/**
 Copyright (c) 2015 Qualcomm Education, Inc.
 All rights reserved.


 Redistribution and use in source and binary forms, with or without modification, are permitted (subject to the limitations in the disclaimer below) provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

 * Neither the name of Qualcomm Education, Inc. nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

 NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 **/

package org.edx.mobile.discussion;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DiscussionThread implements Serializable, IAuthorData {

    public enum ThreadType {
        @SerializedName("discussion")
        DISCUSSION (0),

        @SerializedName("question")
        QUESTION (1);

        private final int value;

        private ThreadType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @SerializedName("id")
    private String identifier;
    private ThreadType type;
    private String courseId;
    private String topicId;
    private int groupId;
    private String groupName;
    private String title;
    private String rawBody;
    private String renderedBody;
    private String author;
    private String authorLabel;
    private int commentCount = 0;
    private int unreadCommentCount = 0;
    private String commentListUrl;
    private boolean hasEndorsed = false;
    private boolean pinned = false;
    private boolean closed = false;
    private boolean following = false;
    private boolean abuseFlagged = false;
    private boolean voted = false;
    private int voteCount = 0;
    private Date createdAt;
    private Date updatedAt;
    private List<String> editableFields;
    private boolean read = false;

    public String getIdentifier() {
        return identifier;
    }

    public ThreadType getType() {
        return type;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTopicId() {
        return topicId;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTitle() {
        return title;
    }

    public String getRawBody() {
        return rawBody;
    }

    public String getRenderedBody() {
        return renderedBody;
    }

    public String getAuthor() {
        if (author == null || author.isEmpty()) {
            return "anonymous";
        }
        return author;
    }

    public String getAuthorLabel() {
        return authorLabel;
    }

    public String getCommentListUrl() {
        return commentListUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getUnreadCommentCount() {
        return unreadCommentCount;
    }

    public boolean isHasEndorsed() {
        return hasEndorsed;
    }

    public boolean isPinned() {
        return pinned;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isAbuseFlagged() {
        return abuseFlagged;
    }

    public boolean isVoted() {
        return voted;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<String> getEditableFields() {
        return editableFields;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
        if (read) {
            unreadCommentCount = 0;
        }
    }

    public void incrementCommentCount() {
        ++commentCount;
        ++unreadCommentCount;
        read = false;
    }

    public boolean hasSameId(@NonNull DiscussionThread discussionThread) {
        return discussionThread.getIdentifier().equals(identifier);
    }

    public boolean containsComment(@NonNull DiscussionComment comment) {
        return comment.getThreadId().equals(identifier);
    }

}
