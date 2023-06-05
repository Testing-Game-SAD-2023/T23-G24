package com.g24.authentication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g24.authentication.entity.PasswordResetToken;
import com.g24.authentication.entity.User;
import com.g24.authentication.repository.PasswordResetTokenRepository;
import com.g24.authentication.service.PasswordResetTokenService;


@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService{


    @Autowired PasswordResetTokenRepository passwordResetTokenRepository;

    @Override

    public PasswordResetToken findByToken(String token) {

        return passwordResetTokenRepository.findByToken(token);

    }


    @Override

    public PasswordResetToken findByUser(User user) {

        return passwordResetTokenRepository.findByUser(user);

    }

    @Override

    public void delete(PasswordResetToken token) {

        passwordResetTokenRepository.delete(token);

    }

    @Override

    public void save(PasswordResetToken token) {

        passwordResetTokenRepository.save(token);

    }
}