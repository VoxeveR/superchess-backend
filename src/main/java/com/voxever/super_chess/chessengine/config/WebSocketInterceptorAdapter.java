package com.voxever.super_chess.chessengine.config;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class WebSocketInterceptorAdapter implements ChannelInterceptor {

    public final WebSocketAuthenticatorService webSocketAuthenticatorService;

    @Autowired
    public WebSocketInterceptorAdapter(WebSocketAuthenticatorService webSocketAuthenticatorService){
        this.webSocketAuthenticatorService = webSocketAuthenticatorService;
    }

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        if(StompCommand.CONNECT == accessor.getCommand()) {
            String jwtToken = accessor.getFirstNativeHeader("Authorization");

            UsernamePasswordAuthenticationToken user = webSocketAuthenticatorService.getAuthenticationOrFail(jwtToken);

            accessor.setUser(user);
        }
        return message;
    }
}
