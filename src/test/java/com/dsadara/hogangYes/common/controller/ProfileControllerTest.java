package com.dsadara.hogangYes.common.controller;

import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProfileControllerTest {

    @Test
    public void prod1_profile이_조회된다() throws Exception {
        // given
        String expectedProfile = "prod1";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile("dev");
        env.addActiveProfile("local");
        env.addActiveProfile(expectedProfile);

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(expectedProfile, profile);
    }

    @Test
    public void prod1_profile이_없으면_첫_번째가_조회된다() throws Exception {
        // given
        String expectedProfile = "dev";
        MockEnvironment env = new MockEnvironment();

        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("local");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(expectedProfile, profile);
    }

    @Test
    public void active_profile이_없으면_default가_조회된다() throws Exception {
        // given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        assertEquals(expectedProfile, profile);
    }

}