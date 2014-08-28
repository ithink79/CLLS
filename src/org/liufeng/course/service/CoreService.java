package org.liufeng.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.liufeng.course.message.resp.TextMessage;
import org.liufeng.course.util.BaiduMapUtil;
import org.liufeng.course.util.MessageUtil;
import org.liufeng.course.util.MySQLUtil;
import org.liufeng.course.message.resp.Article;
import org.liufeng.course.message.resp.NewsMessage;
import org.liufeng.course.pojo.BaiduPlace;
import org.liufeng.course.pojo.UserLocation;

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
					article.setTitle("武汉创联龙盛文化传媒有限公司简介，点击获取更多精彩内容！");
					article.setDescription("");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVA7gqbvOicO9of93wTZgd2saXgdsyf1cKezlTfCMrtPC0iauPsmC4ERBSr7sdTvarZxVPyRDh3OjfA/640");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=1&sn=e080ae87accf151c59b783a77eabb04e#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("2".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("我们的顶级品牌服务商！");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmyqAl4Pcfs1qWVspTIiajnzyrTZVxKKz0AictiaeEDEq8w2ZTSNvqXWfbw/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=2&sn=b425657e7ce531545e0b2f17e9bcbadb#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("我们的业务辐射地区范围！");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmMzH8lZOuD6PlA7YOxDyfOpHuoLbRlS9esGMrpZExe2HgIe0oGh0ia7A/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=3&sn=024a812f7cd64e22f9ba4d235f8c0d93#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("我们的业务服务范围！");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmLDkbLIibmjuYrGnHESd2fJVgodsKHSTaBjtomUYRZ4LIPMeBFugQglQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=4&sn=73c4217f877532658041d1069582c9e3#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("往期活动盘点");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmicoyQwDsdCwN7m317icCdhib8N50xCWYmY9w8ic50vE1rK2stdibWDUdeiaQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762402&idx=1&sn=b8d2e7d1d50e84012a0eff53e8617a9c#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
					} else if ("6".equals(content)) {
						StringBuffer buffer = new StringBuffer();
						buffer.append("[愉快]").append("暂时还没有最新活动").append("\n\n");
						buffer.append("你可以去看看别的，或者看看附近有什么好吃好玩的地方也不错哦").append("\n");
						respContent = buffer.toString();
						textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
					} else if ("7".equals(content)) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("[愉快]").append("我们在这里等着您！").append("\n\n");
					buffer.append("公司地址：").append("\n");
					buffer.append("武汉市江汉区京汉大道前进一路").append("\n");
					buffer.append("万科金色家园二期商业街321-323商铺").append("\n");
					buffer.append("公司电话：").append("\n");
					buffer.append("027-85666838");
					respContent = buffer.toString();
					textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
				} else if ("附近".equals(content)) {
					respContent = getUsage();
					textMessage.setContent(respContent);
					respXml=MessageUtil.messageToXml(textMessage);
				} else if (content.startsWith("附近")) {
					String keyWord = content.replaceAll("附近", "").trim();
					// 获取用户最后一次发送的地理位置
					UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
					// 未获取到
					if (null == location) {
						respContent = getUsage();
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					} else {
						// 根据转换后（纠偏）的坐标搜索周边POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
						// 未搜索到POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/难过，您发送的位置附近未搜索到“%s”信息！", keyWord);
						} else {
							articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
							// 回复图文消息
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(new Date().getTime());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticles(articleList);
							newsMessage.setArticleCount(articleList.size());
							respXml = MessageUtil.messageToXml(newsMessage);
						}
					}
				}else {
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
				// 用户发送的经纬度
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// 坐标转换后的经纬度
				String bd09Lng = null;
				String bd09Lat = null;
				// 调用接口转换坐标
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// 保存用户地理位置
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[愉快]").append("成功接收您的位置！").append("\n\n");
				buffer.append("您可以输入搜索关键词获取周边信息了，例如：").append("\n");
				buffer.append("        附近ATM").append("\n");
				buffer.append("        附近KTV").append("\n");
				buffer.append("        附近酒店").append("\n");
				buffer.append("必须以“附近”两个字开头！");
				respContent = buffer.toString();
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
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
					article1.setTitle("创联龙盛文化传媒欢迎您！");
					article1.setDescription("欢迎关注创联龙盛文化传媒！点击进入了解更多资讯。");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2Mm2Q1vQfIakicbKBCENwGePHWlDrr0oe11uuFI9xfCzE329wndyA8bAqQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200760002&idx=1&sn=0048218c75f50ec5eaa40cd51ba6add9#rd");
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
	/**
	 * 默认随机回答
	 * 
	 * @return
	 */
	private static String getDefaultAnswer() {
		String[] answer = { "要不我们聊点别的？", "恩？你到底在说什么呢？", "没有听懂你说的，能否换个说法",
				"虽然不明白你的意思，但我却能用心去感受", "听得我一头雾水，阁下的只是真是渊博啊，膜拜~",
				"哎，我小学语文是体育老师教的，理解起来有点困难啊", "是世界变化太快，还是我不够有才？为何你说话我不明白？", };
		return answer[getRandomNumber(answer.length)];
	}
	/**
	 * 使用说明
	 * 
	 * @return
	 */
	private static String getUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("周边搜索使用说明").append("\n\n");
		buffer.append("1）发送地理位置").append("\n");
		buffer.append("点击窗口底部的“+”按钮，选择“位置”，点“发送”").append("\n\n");
		buffer.append("2）指定关键词搜索").append("\n");
		buffer.append("格式：附近+关键词\n例如：附近ATM、附近KTV、附近厕所");
		return buffer.toString();
	}
	
	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
}
