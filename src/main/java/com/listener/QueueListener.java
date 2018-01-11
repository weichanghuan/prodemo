package com.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收Activemq发送的消息
 * <p>
 * Title: MyMessageListener
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.cn
 * </p>
 * 
 * @version 1.0
 */
public class QueueListener implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        try {
            // 接收到消息
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.err.println("activemq的消息是==》" + text);
            logger.debug("activemq的消息是==》" + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
