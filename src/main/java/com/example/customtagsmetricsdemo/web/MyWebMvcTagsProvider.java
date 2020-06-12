package com.example.customtagsmetricsdemo.web;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTags;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsContributor;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyWebMvcTagsProvider implements WebMvcTagsProvider {
    private final boolean ignoreTrailingSlash;
    private final List<WebMvcTagsContributor> contributors;

    public MyWebMvcTagsProvider(boolean ignoreTrailingSlash, List<WebMvcTagsContributor> contributors) {
        this.ignoreTrailingSlash = ignoreTrailingSlash;
        this.contributors = contributors;
    }

    @Override
    public Iterable<Tag> getTags(HttpServletRequest request, HttpServletResponse response, Object handler,
                                 Throwable exception) {
        Tags tags = Tags.of(WebMvcTags.status(response), WebMvcTags.outcome(response));
        for (WebMvcTagsContributor contributor : this.contributors) {
            tags = tags.and(contributor.getTags(request, response, handler, exception));
        }
        return tags;
    }

    @Override
    public Iterable<Tag> getLongRequestTags(HttpServletRequest request, Object handler) {
        Tags tags = Tags.of(WebMvcTags.method(request), WebMvcTags.uri(request, null, this.ignoreTrailingSlash));
        for (WebMvcTagsContributor contributor : this.contributors) {
            tags = tags.and(contributor.getLongRequestTags(request, handler));
        }
        return tags;
    }
}
