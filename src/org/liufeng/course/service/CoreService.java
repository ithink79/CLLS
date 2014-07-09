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
 * ���ķ�����
 * 
 * @author liufeng
 * @date 2013-09-29
 */
public class CoreService {
	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml��ʽ����Ϣ����
		String respXml = null;
		// Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ���ͣ�";
		try {
			// ����parseXml��������������Ϣ
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// ���ͷ��ʺ�
			String fromUserName = requestMap.get("FromUserName");
			// ������΢�ź�
			String toUserName = requestMap.get("ToUserName");
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			// �ظ��ı���Ϣ
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

			// �ı���Ϣ
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("�˳�Ƽ���Ŀ���");
					article.setDescription("�˳�Ƽ����������ĳ��ｻ��Ӧ��");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/YlPL4VTWj1piagCvibxo5S6UneOtdFGtjXQKHNVCD7ZCjCpFX0NDbRxrY8SyAKwExsIibkKBAue0BQKjPlyFsNjYQ/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MjI2NTIzNg==&mid=200745164&idx=1&sn=98d93be02fb752b072529eeb098b89d0#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("����������·");
					article.setDescription("������ʢ�������ڲ�ͬ�ľ�����");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/7bm7gYcrtbyrbpbyalm0ZtpS32RtvBGF8ggNjJFm0cU05vibvo0qr7m47KlMIjRKS2qNwum7onyHwud0rV2wpPw/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200350057&idx=1&sn=37e64d3b2beb4d8e7f5c68a5c9c8c7e6#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {
					Article article = new Article();
					article.setTitle("������С��Ƭ");
					article.setDescription("������ʢ�������ڲ�ͬ�ľ�����");
					article.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/7bm7gYcrtbyrbpbyalm0ZtpS32RtvBGF8ggNjJFm0cU05vibvo0qr7m47KlMIjRKS2qNwum7onyHwud0rV2wpPw/0");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzA5MTY4NTczNQ==&mid=200350057&idx=1&sn=37e64d3b2beb4d8e7f5c68a5c9c8c7e6#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("4".equals(content)) {
					Article article = new Article();
					article.setTitle("����С�ĵ�");
					article.setDescription("������ʢ�������ڲ�ͬ�ľ�����");
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
			// ͼƬ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "�����͵���ͼƬ��Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "�����͵���������Ϣ��";
			}
			// ��Ƶ��Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
				respContent = "�����͵�����Ƶ��Ϣ��";
			}
			// ����λ����Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}
			// ������Ϣ
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "�����͵���������Ϣ��";
			}
			// �¼�����
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");
				// ��ע
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					Article article1 = new Article();
					article1.setTitle("�˳�Ƽ����޹�˾");
					article1.setDescription("��ӭ��ע\"�˳�Ƽ�\",���ྫ�����������鿴...");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/YlPL4VTWj1oqqsQXPOgwEvvgDrYcQb3GJFErkEmll5DCS1LZsiap8Vcv0WyTKMIUdMmR9bPCibuqDIpsJxDnLCAg/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MjM5MjI2NTIzNg==&mid=200751013&idx=1&sn=88e3b771839ae068dcf3866a3cfd2791#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				}
				// ȡ����ע
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}
				// ɨ���������ά��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// TODO ����ɨ���������ά���¼�
				}
				// �ϱ�����λ��
				else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// TODO �����ϱ�����λ���¼�
				}
				// �Զ���˵�
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO ����˵�����¼�
				}
			}
			// �����ı���Ϣ������
			// textMessage.setContent(respContent);
			// ���ı���Ϣ����ת����xml
			// respXml = MessageUtil.messageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}

	private static String getDefaultAnswer() {
		String[] answer = { "Ҫ�������ĵ��ģ�", "�����㵽����˵ʲô�أ�", "û��������˵�ģ��ܷ񻻸�˵��",
				"��Ȼ�����������˼������ȴ������ȥ����", "������һͷ��ˮ�����µ�ֻ������Ԩ������Ĥ��~",
				"������Сѧ������������ʦ�̵ģ���������е����Ѱ�", "������仯̫�죬�����Ҳ����вţ�Ϊ����˵���Ҳ����ף�", };
		return answer[getRandomNumber(answer.length)];
	}

	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
}
