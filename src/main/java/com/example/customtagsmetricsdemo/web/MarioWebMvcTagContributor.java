package com.example.customtagsmetricsdemo.web;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MarioWebMvcTagContributor implements WebMvcTagsContributor {

    private static final Tags MARIO_TAG = Tags.of("myCustomTagViaContributor", "it's a me, mario");

    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response, Object handler, Throwable exception) {
        return MARIO_TAG;
    }

    @Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        return MARIO_TAG;
    }
}
