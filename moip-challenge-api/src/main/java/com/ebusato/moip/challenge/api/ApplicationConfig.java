package com.ebusato.moip.challenge.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ebusato.moip.challenge.persistence.MoipChallengePersistenceConfig;
import com.ebusato.moip.challenge.service.config.MoipChallengeServiceConfig;

@Configuration
@Import(value = {MoipChallengePersistenceConfig.class, MoipChallengeServiceConfig.class})
public class ApplicationConfig {

}
