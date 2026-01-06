package com.group8.service.impl;

import com.group8.entity.User;
import com.group8.entity.Role;
import com.group8.mapper.UserMapper;
import com.group8.mapper.RoleMapper;
import com.group8.service.UserService;
import com.group8.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public User getById(Integer id) {
        if (id == null) {
            throw new BusinessException("用户ID不能为空");
        }
        return userMapper.getById(id);
    }
    
    @Override
    public User getByUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        return userMapper.getByUsername(username);
    }
    
    @Override
    public List<User> list(Map<String, Object> params) {
        return userMapper.list(params);
    }
    
    @Override
    public int count(Map<String, Object> params) {
        return userMapper.count(params);
    }
    
    @Override
    @Transactional
    public User create(User user) {
        if (user == null) {
            throw new BusinessException("用户信息不能为空");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BusinessException("密码不能为空");
        }
        
        // 检查用户名是否已存在
        User existingUser = userMapper.getByUsername(user.getUsername());
        if (existingUser != null) {
            throw new BusinessException("用户名已存在");
        }
        
        // 设置默认值
        if (user.getStatus() == null) {
            user.setStatus(1); // 1-正常
        }
        
        int result = userMapper.create(user);
        if (result != 1) {
            throw new BusinessException("创建用户失败");
        }
        
        return user;
    }
    
    @Override
    @Transactional
    public User update(User user) {
        if (user == null) {
            throw new BusinessException("用户信息不能为空");
        }
        if (user.getId() == null) {
            throw new BusinessException("用户ID不能为空");
        }
        
        // 检查用户是否存在
        User existingUser = userMapper.getById(user.getId());
        if (existingUser == null) {
            throw new BusinessException("用户不存在");
        }
        
        // 检查用户名是否已被其他用户使用
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            User userByUsername = userMapper.getByUsername(user.getUsername());
            if (userByUsername != null && !userByUsername.getId().equals(user.getId())) {
                throw new BusinessException("用户名已存在");
            }
        }
        
        int result = userMapper.update(user);
        if (result != 1) {
            throw new BusinessException("更新用户失败");
        }
        
        return userMapper.getById(user.getId());
    }
    
    @Override
    @Transactional
    public int updateStatus(Integer id, Integer status) {
        if (id == null) {
            throw new BusinessException("用户ID不能为空");
        }
        if (status == null) {
            throw new BusinessException("用户状态不能为空");
        }
        if (status != 1 && status != 2) {
            throw new BusinessException("无效的用户状态");
        }
        
        // 检查用户是否存在
        User existingUser = userMapper.getById(id);
        if (existingUser == null) {
            throw new BusinessException("用户不存在");
        }
        
        return userMapper.updateStatus(id, status);
    }
    
    @Override
    @Transactional
    public int delete(Integer id) {
        if (id == null) {
            throw new BusinessException("用户ID不能为空");
        }
        
        // 检查用户是否存在
        User existingUser = userMapper.getById(id);
        if (existingUser == null) {
            throw new BusinessException("用户不存在");
        }
        
        return userMapper.delete(id);
    }
    
    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty()) {
            throw new BusinessException("用户名不能为空");
        }
        if (password == null || password.isEmpty()) {
            throw new BusinessException("密码不能为空");
        }
        
        User user = userMapper.getByUsername(username);
        if (user == null) {
            throw new BusinessException("用户名或密码错误");
        }
        
        // 密码验证（实际项目中应该使用加密存储和验证）
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("用户名或密码错误");
        }
        
        if (user.getStatus() != 1) {
            throw new BusinessException("用户已被禁用");
        }
        
        return user;
    }
    
    @Override
    public List<Role> getUserRoles(Integer userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        return roleMapper.listByUserId(userId);
    }
    
    @Override
    @Transactional
    public int assignRole(Integer userId, Integer roleId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        if (roleId == null) {
            throw new BusinessException("角色ID不能为空");
        }
        
        // 检查用户是否存在
        User user = userMapper.getById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        
        // 检查角色是否存在
        Role role = roleMapper.getById(roleId);
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        
        // 先取消用户现有角色
        roleMapper.unassignRole(userId);
        
        // 分配新角色
        return roleMapper.assignRole(userId, roleId);
    }
}