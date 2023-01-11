package io.github.guimatech.domdaterra.infra.init;

import io.github.guimatech.domdaterra.domain.User;
import io.github.guimatech.domdaterra.infra.out.db.repository.UserRepository;
import io.github.guimatech.domdaterra.shared.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsertDataForUsers {

    @Autowired
    private UserRepository userRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        users();
    }

    private void users() {
        if (!userRepository.findAll().isEmpty())
            return;

        userRepository.saveAll(
                List.of(
                        User.builder()
                                .username("francimar")
                                .password(StringUtil.encrypt("22nwtt2q"))
                                .build(),
                        User.builder()
                                .username("thainarah")
                                .password(StringUtil.encrypt("xjh21h08"))
                                .build(),
                        User.builder()
                                .username("nubia")
                                .password(StringUtil.encrypt("imygwdp1"))
                                .build(),
                        User.builder()
                                .username("suelio")
                                .password(StringUtil.encrypt("wv7u1ilr"))
                                .build()
                )
        );
    }
}