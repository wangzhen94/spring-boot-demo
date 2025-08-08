package com.xkcoding.helloworld.controller;

import com.xkcoding.entity.User;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.future.ConnectFuture;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;
import org.apache.sshd.common.channel.StreamingChannel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * Description: TODO
 *
 * @author wangzhen
 * @since 2024/6/23 21:40
 */
@RestController
@RequestMapping("cmd")
public class SshController {

  @PostMapping("/")
  public String createUser(@RequestBody User user) {
    try (SshClient client = SshClient.setUpDefaultClient()) {
      client.start();
      ConnectFuture future = client.connect("root", "localhost", 2000);
      future.await(10, TimeUnit.SECONDS);
      ClientSession session = future.getSession();
      session.addPasswordIdentity("rootpassword");
      session.auth().verify(10, TimeUnit.SECONDS);


      try (ChannelExec channel = session.createExecChannel("useradd -m " + user.getName())) {
        channel.setStreaming(StreamingChannel.Streaming.Async);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        channel.setOut(bos);
        channel.open().verify(10, TimeUnit.SECONDS);
        channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.ordinal());
        return bos.toString("UTF-8");
      }

    } catch (IOException e) {
    }


    return "SUCCESS";
  }
}
