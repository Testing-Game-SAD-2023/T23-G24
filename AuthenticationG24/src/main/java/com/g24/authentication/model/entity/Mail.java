package com.g24.authentication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Mail 
{
	private String type;
	private String from;
    private String to;
    private String subject;
    private String htmlTemplate;
    private Map<String, Object> model;
}