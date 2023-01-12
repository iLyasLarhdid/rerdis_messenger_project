package com.larhdid.messenger.Repository;

import com.larhdid.messenger.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMessageRepository extends JpaRepository<Message,String> {
}
