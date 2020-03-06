package com.epsilon.lx.dto;

import com.epsilon.lx.entities.ResourcePath;
import com.epsilon.lx.entities.Video;

import java.util.List;

public class VideoDTO {

    private Video video;
    private List<ResourcePath> resourcePathList;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public List<ResourcePath> getResourcePathList() {
        return resourcePathList;
    }

    public void setResourcePathList(List<ResourcePath> resourcePathList) {
        this.resourcePathList = resourcePathList;
    }


}
