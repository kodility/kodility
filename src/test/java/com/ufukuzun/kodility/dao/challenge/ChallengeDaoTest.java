package com.ufukuzun.kodility.dao.challenge;

import com.ufukuzun.kodility.AbstractDaoTest;
import com.ufukuzun.kodility.domain.challenge.Challenge;
import com.ufukuzun.kodility.domain.user.User;
import com.ufukuzun.kodility.testutils.builder.ChallengeBuilder;
import com.ufukuzun.kodility.testutils.builder.UserBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ChallengeDaoTest extends AbstractDaoTest {

    @Autowired
    private ChallengeDao dao;

    @Test
    public void shouldFindAllApproved() {
        User user = new UserBuilder().persist(getCurrentSession());
        Challenge challenge1 = new ChallengeBuilder().user(user).approved(true).persist(getCurrentSession());
        Challenge challenge2 = new ChallengeBuilder().user(user).approved(false).persist(getCurrentSession());
        Challenge challenge3 = new ChallengeBuilder().user(user).approved(true).persist(getCurrentSession());

        flushAndClear();

        List<Challenge> resultList = dao.findAllApproved();

        assertThat(resultList, hasSize(2));
        assertThat(resultList, hasItems(challenge1, challenge3));
        assertThat(resultList, not(hasItem(challenge2)));
    }

}