package com.dokito.letshelp;

import com.dokito.letshelp.data.models.Role;
import com.dokito.letshelp.data.models.User;
import com.dokito.letshelp.data.repositories.UserRepository;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Set<Role> authorities = new LinkedHashSet<>();

        authorities.add(new Role("ROLE_USER"));
//        authorities.add(new Role("ROLE_ADMIN"));
//        authorities.add(new Role("ROLE_ROOT"));
//        authorities.add(new Role("ROLE_CONTRIBUTOR"));

        List<Role> collect = new ArrayList<>(authorities);

        collect.forEach(r ->
                System.out.println(r.getAuthority()));

        System.out.println(authorities.iterator().next().getAuthority());

        System.out.println("New way..");
        authorities.forEach(r ->
                System.out.println(r.getAuthority()));
    }
}
