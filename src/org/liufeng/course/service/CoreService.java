package org.liufeng.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;

import com.sun.corba.se.spi.legacy.connection.LegacyServerSocketEndPointInfo;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		// 默认返回的文本消息内容
		String respContent = "未知的消息类型！";
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(new Date().getTime());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			List<Article> articleList = new ArrayList<Article>();

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("创联龙盛文化传媒公司");
					article.setDescription("武汉市江汉区京汉大道万科金色家园二期商业街321室");
					article.setPicUrl("https://weixinshgj.duapp.com/image/mp.jpg");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {

				} else if ("4".equals(content)) {

				} else if ("5".equals(content)) {

				} else if ("6".equals(content)) {

				} else if ("7".equals(content)) {

				} else {
					textMessage.setContent(getDefaultAnswer());
					respXml = MessageUtil.messageToXml(textMessage);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 语音消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是语音消息！";
			}
			// 视频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "您发送的是视频消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 关注
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					Article article1 = new Article();
					article1.setTitle("创联龙盛文化传媒公司");
					article1.setDescription("武汉市江汉区京汉大道万科金色家园二期商业街321室");
					article1.setPicUrl("https://weixinshgj.duapp.com/image/logo2.jpg");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					Article article2 = new Article();
					article2.setTitle("感谢您关注创联龙盛文化传媒，点击获取更多资讯");
					article2.setDescription("");
					article2.setPicUrl("https://weixinshgj.duapp.com/image/logo_s.jpg");
					article2.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200332988&idx=1&sn=c037f49bd41b4ed5622e0ac2e0e6a14a#rd");
					articleList.add(article1);
					articleList.add(article2);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				}
				// 取消关注
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
				}
				// 扫描带参数二维码
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO 处理扫描带参数二维码事件
				}
				// 上报地理位置
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO 处理上报地理位置事件
				}
				// 自定义菜单
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 处理菜单点击事件
				}
			}
			// 设置文本消息的内容
			// textMessage.setContent(respContent);
			// 将文本消息对象转换成xml
			// respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	private static String getDefaultAnswer() {
		String[] answer = { "要不我们聊点别的？", "恩？你到底在说什么呢？", "没有听懂你说的，能否换个说法",
				"虽然不明白你的意思，但我却能用心去感受", "听得我一头雾水，阁下的只是真是渊博啊，膜拜~",
				"哎，我小学语文是体育老师教的，理解起来有点困难啊", "是世界变化太快，还是我不够有才？为何你说话我不明白？", };
		return answer[getRandomNumber(answer.length)];
	}

	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
}
