package com.forloop.controller;


import com.forloop.model.*;
import com.forloop.persistence.PersistenceManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class UserController {

    private static EntityManager entityManager = PersistenceManager.getInstance().getEntityManager();

    public UserController() {
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User registration(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email) {

        User user = new User(username, password, email);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return user;
    }



    @GetMapping(value = "/users", produces = "application/json")
    public List<Tag> getUsers() {

//        populateDB();

        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery(
                "SELECT u FROM User u").getResultList();

        List<Tag> channels = entityManager.createNamedQuery("getAllTags")
                .getResultList();
        entityManager.getTransaction().commit();

        List<User> usersByChannel = entityManager.createNamedQuery("findUsersByChannel").setParameter("user_id", 1l).getResultList();

        List<User> userByName = entityManager.createNamedQuery("findUserByName").setParameter("name", "Feri").getResultList();

        List<User> getFriends = entityManager.createNamedQuery("getFriends").setParameter("user_id", 2l).getResultList();

        System.out.println("Friend is" + getFriends.get(0).getName());

        System.out.println(userByName.get(0).getEmail());

        for (User user : usersByChannel){
            System.out.println(user.getName());
        }

        entityManager.close();

        return channels;

    }

//    private static void populateDB() {
//        User user1 = new User("Jocó", "pass", "email", new ArrayList<>(), 0);
//        User user2 = new User("Karesz", "pass1", "email2", new ArrayList<>(), 1);
//        User user3 = new User("Feri", "pass1", "email3", new ArrayList<>(), 1);
//        User user4 = new User("Bela", "pass1", "email4", new ArrayList<>(), 1);
//        User user5 = new User("Juci", "pass1", "email5", new ArrayList<>(), 1);
//
//        Tag tag1 = new Tag("Tag1", new ArrayList<>());
//        Tag tag2 = new Tag("Tag2", new ArrayList<>());
//        Tag tag3 = new Tag("Tag3", new ArrayList<>());
//        Tag tag4 = new Tag("Tag4", new ArrayList<>());
//        Tag tag5 = new Tag("Tag5", new ArrayList<>());
//
//        List<Tag> tagList = new ArrayList<>();
//
//        tagList.add(tag1);
//        tagList.add(tag2);
//        tagList.add(tag3);
//        tagList.add(tag4);
//        tagList.add(tag5);
//
//        List<User> userList = new ArrayList<>();
//        List<User> userList2 = new ArrayList<>();
//
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
//        userList2.add(user2);
//        userList2.add(user3);
//        userList2.add(user4);
//        userList2.add(user5);
//
//        Channel channel1 = new Channel("Games", userList, user1, tagList, new ArrayList<>());
//        ChannelMessage channelMessage = new ChannelMessage("New channelmsg", user1, channel1);
//
//        Channel channel2 = new Channel("Politics", userList2, user2, tagList, new ArrayList<>());
//
//        Reply reply = new Reply(channelMessage, "replyFromJC", user1);
//        channelMessage.getReplies().add(reply);
//
//        UserRelation userRelation = new UserRelation(user1, 2);
//        UserRelation userRelation2 = new UserRelation(user2, 3);
//        userRelation2.setRelationState(RelationState.ACCEPTED);
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//        entityManager.persist(user1);
//        entityManager.persist(user2);
//        entityManager.persist(user3);
//        entityManager.persist(user4);
//        entityManager.persist(user5);
//        entityManager.persist(tag1);
//        entityManager.persist(tag2);
//        entityManager.persist(tag3);
//        entityManager.persist(tag4);
//        entityManager.persist(tag5);
//        entityManager.persist(channel1);
//        entityManager.persist(channel2);
//        entityManager.persist(channelMessage);
//        entityManager.persist(reply);
//        entityManager.persist(userRelation);
//        entityManager.persist(userRelation2);
//        transaction.commit();
//
//    }


}
