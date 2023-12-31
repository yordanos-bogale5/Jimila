package com.seha.jimila.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seha.jimila.databinding.ItemContainerReceivedMessageBinding;
import com.seha.jimila.databinding.ItemContainerSentMessageBinding;
import com.seha.jimila.models.ChatMessage;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final String senderId;
    private final Bitmap receiverProfileImage;
    private final List<ChatMessage> chatMessages;

    public static final int VIEW_TYPE_SENT = 1;
    public static final int VIEW_TYPE_RECEIVED = 2;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_SENT) {
            return new SentMessageViewHolder(
                    ItemContainerSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        } else {
            return new ReceivedMessageViewHolder(
                    ItemContainerReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
          if(getItemViewType(position) == VIEW_TYPE_SENT) {
              ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
          } else {
              ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position), receiverProfileImage);
          }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderId)) {
            return VIEW_TYPE_SENT;
        } else return VIEW_TYPE_RECEIVED;
    }

    public ChatAdapter(String senderId, Bitmap receiverProfileImage, List<ChatMessage> chatMessages) {
        this.senderId = senderId;
        this.receiverProfileImage = receiverProfileImage;
        this.chatMessages = chatMessages;
    }


    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerSentMessageBinding binding;

        SentMessageViewHolder(ItemContainerSentMessageBinding itemContainerSentMessageBinding) {
            super(itemContainerSentMessageBinding.getRoot());
            binding = itemContainerSentMessageBinding;
        }

        void setData(ChatMessage chatMessage) {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private final ItemContainerReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemContainerReceivedMessageBinding itemContainerReceivedMessageBinding) {
            super(itemContainerReceivedMessageBinding.getRoot());
            binding = itemContainerReceivedMessageBinding;
        }

        void setData(ChatMessage chatMessage, Bitmap receivedProfileImage) {
            binding.textMessage.setText(chatMessage.message);
            binding.textDateTime.setText(chatMessage.dateTime);
            binding.imageProfile.setImageBitmap(receivedProfileImage);
        }
    }
}
