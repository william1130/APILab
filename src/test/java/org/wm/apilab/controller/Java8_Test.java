package org.wm.apilab.controller;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.wm.apilab.dao.SysUserMapper;
import org.wm.apilab.model.SysUser;

@SpringBootTest
@RunWith(SpringRunner.class)
@SuppressWarnings("rawtypes")
public class Java8_Test {

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void test() {

        List<String> userList = getUserList();

        StringBuilder result = new StringBuilder();
        List<CompletableFuture> futures =
                userList.stream()
                        .map(user -> CompletableFuture.completedFuture(user)
                                .thenApplyAsync(s -> delayedUpperCase(s)))
                        .collect(Collectors.toList());
        CompletableFuture allOf =
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                        .whenComplete((v, th) -> {
                            futures.forEach(cf -> System.out.println(cf.getNow(null)));
                            result.append("done");
                        });
        allOf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    private List<String> getUserList() {
        List<String> userName = new ArrayList<String>();
        List<SysUser> sysUser = sysUserMapper.selectAll();
        sysUser.forEach(
                user -> userName.add(Optional.ofNullable(user.getUsername()).orElse("null")));
        return userName;
    }

    private Object delayedUpperCase(Object s) {
        return s.toString().toUpperCase();
    }

    public boolean isUpperCase(Object object) {

        return true;
    }
}
