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
					article.setTitle("盟宠科技项目简介");
					article.setDescription("盟宠科技缔造最好玩的宠物交友应用");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/YlPL4VTWj1piagCvibxo5S6UneOtdFGtjXQKHNVCD7ZCjCpFX0NDbRxrY8SyAKwExsIibkKBAue0BQKjPlyFsNjYQ/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MjI2NTIzNg==&mid=200745164&idx=1&sn=98d93be02fb752b072529eeb098b89d0#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("宠物新手上路");
					article.setDescription("创联龍盛缔造与众不同的经典神话");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/7bm7gYcrtbyrbpbyalm0ZtpS32RtvBGF8ggNjJFm0cU05vibvo0qr7m47KlMIjRKS2qNwum7onyHwud0rV2wpPw/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200350057&idx=1&sn=37e64d3b2beb4d8e7f5c68a5c9c8c7e6#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {
					Article article = new Article();
					article.setTitle("萌萌哒小照片");
					article.setDescription("创联龍盛缔造与众不同的经典神话");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/7bm7gYcrtbyrbpbyalm0ZtpS32RtvBGF8ggNjJFm0cU05vibvo0qr7m47KlMIjRKS2qNwum7onyHwud0rV2wpPw/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200350057&idx=1&sn=37e64d3b2beb4d8e7f5c68a5c9c8c7e6#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("4".equals(content)) {
					Article article = new Article();
					article.setTitle("养宠小心得");
					article.setDescription("创联龍盛缔造与众不同的经典神话");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/7bm7gYcrtbyrbpbyalm0ZtpS32RtvBGF8ggNjJFm0cU05vibvo0qr7m47KlMIjRKS2qNwum7onyHwud0rV2wpPw/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200350057&idx=1&sn=37e64d3b2beb4d8e7f5c68a5c9c8c7e6#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} /*else if ("5".equals(content)) {

				} else if ("6".equals(content)) {

				} */else {
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
					article1.setTitle("盟宠科技有限公司");
					article1.setDescription("欢迎关注\"盟宠科技\",更多精彩内容请点击查看...");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/YlPL4VTWj1oqqsQXPOgwEvvgDrYcQb3GJFErkEmll5DCS1LZsiap8Vcv0WyTKMIUdMmR9bPCibuqDIpsJxDnLCAg/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MjI2NTIzNg==&mid=200751013&idx=1&sn=88e3b771839ae068dcf3866a3cfd2791#rd");
					articleList.add(article1);
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
