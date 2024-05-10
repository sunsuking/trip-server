package com.ssafy.trip.domain.social.service;

import com.ssafy.trip.domain.social.dto.SocialData;
import com.ssafy.trip.domain.social.entity.Social;
import com.ssafy.trip.domain.social.mapper.SocialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService{

    private final SocialMapper socialMapper;

    @Override
    public List<Social> findSocials() {
        return socialMapper.findSocials();
    }

    @Override
    public Optional<Social> findSocialById(long socialId) {
        return socialMapper.findSocialById(socialId);
    }

    @Override
    public void save(SocialData.Create create) {
        socialMapper.save(create);
    }

    @Override
    public void update(SocialData.Update update) {
        socialMapper.update(update);
    }

    @Override
    public void delete(Long socialId) {
        socialMapper.delete(socialId);
    }
}
