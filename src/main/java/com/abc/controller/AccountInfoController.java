/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.abc.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AccountInfoController {

    @RequiresRoles("admin")
    @RequestMapping("/account-info")
    public String home() {
        System.out.println("account-info...");
        return "account-info...";
    }

    @GetMapping("/userLogin")
    public String userLogin(){
        Subject sub = SecurityUtils.getSubject();
        sub.login(new UsernamePasswordToken("cai","123"));
        return "user login success";
    }

    @GetMapping("/adminLogin")
    public String adminLogin(){
        Subject sub = SecurityUtils.getSubject();
        sub.login(new UsernamePasswordToken("bao","123"));
        return "user login success";
    }

}
