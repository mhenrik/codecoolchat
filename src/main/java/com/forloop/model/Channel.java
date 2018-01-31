package com.forloop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        //AscByName
        @NamedQuery(name = "getAllChannels", query = "SELECT c FROM Channel c ORDER BY c.name"),
        @NamedQuery(name = "getChannelsLikeName", query = "SELECT c FROM Channel c WHERE c.name LIKE CONCAT('%', :name, '%')"),
        //AscByName
        @NamedQuery(name = "getChannelsByUserId",
                    query = "SELECT c FROM Channel c INNER JOIN c.userList u WHERE u.id = :userId ORDER BY c.name"),
        @NamedQuery(name = "getChannelsByTagName",
                    query = "SELECT c FROM Channel c INNER JOIN c.tags t WHERE t.name = :tagName")
})
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> userList;

    @ManyToOne
    private User creator;

    @ManyToMany
    @JsonManagedReference
    private List<Tag> tags;

    @OneToMany(mappedBy = "channel")
    @JsonManagedReference
    private List<ChannelMessage> channelMessages;

    public Channel() {
    }

    public Channel(String name, List<User> userList, User creator, List<Tag> tags, List<ChannelMessage> channelMessages) {
        this.name = name;
        this.userList = userList;
        this.creator = creator;
        this.tags = tags;
        this.channelMessages = channelMessages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<ChannelMessage> getChannelMessages() {
        return channelMessages;
    }

    public void setChannelMessages(List<ChannelMessage> channelMessages) {
        this.channelMessages = channelMessages;
    }
}