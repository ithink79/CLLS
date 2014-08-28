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
					article.setTitle("�人������ʢ�Ļ���ý���޹�˾��飬�����ȡ���ྫ�����ݣ�");
					article.setDescription("");
					article.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVA7gqbvOicO9of93wTZgd2saXgdsyf1cKezlTfCMrtPC0iauPsmC4ERBSr7sdTvarZxVPyRDh3OjfA/640");
					article.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=1&sn=e080ae87accf151c59b783a77eabb04e#rd");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("2".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("���ǵĶ���Ʒ�Ʒ����̣�");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmyqAl4Pcfs1qWVspTIiajnzyrTZVxKKz0AictiaeEDEq8w2ZTSNvqXWfbw/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=2&sn=b425657e7ce531545e0b2f17e9bcbadb#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("���ǵ�ҵ����������Χ��");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmMzH8lZOuD6PlA7YOxDyfOpHuoLbRlS9esGMrpZExe2HgIe0oGh0ia7A/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=3&sn=024a812f7cd64e22f9ba4d235f8c0d93#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("���ǵ�ҵ�����Χ��");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmLDkbLIibmjuYrGnHESd2fJVgodsKHSTaBjtomUYRZ4LIPMeBFugQglQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762060&idx=4&sn=73c4217f877532658041d1069582c9e3#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
				} else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("���ڻ�̵�");
					article1.setDescription("");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2MmicoyQwDsdCwN7m317icCdhib8N50xCWYmY9w8ic50vE1rK2stdibWDUdeiaQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200762402&idx=1&sn=b8d2e7d1d50e84012a0eff53e8617a9c#rd");
					articleList.add(article1);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respXml = MessageUtil.messageToXml(newsMessage);
					} else if ("6".equals(content)) {
						StringBuffer buffer = new StringBuffer();
						buffer.append("[���]").append("��ʱ��û�����»").append("\n\n");
						buffer.append("�����ȥ������ģ����߿���������ʲô�óԺ���ĵط�Ҳ����Ŷ").append("\n");
						respContent = buffer.toString();
						textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
					} else if ("7".equals(content)) {
					StringBuffer buffer = new StringBuffer();
					buffer.append("[���]").append("�����������������").append("\n\n");
					buffer.append("��˾��ַ��").append("\n");
					buffer.append("�人�н������������ǰ��һ·").append("\n");
					buffer.append("��ƽ�ɫ��԰������ҵ��321-323����").append("\n");
					buffer.append("��˾�绰��").append("\n");
					buffer.append("027-85666838");
					respContent = buffer.toString();
					textMessage.setContent(respContent);
					respXml = MessageUtil.messageToXml(textMessage);
				} else if ("����".equals(content)) {
					respContent = getUsage();
					textMessage.setContent(respContent);
					respXml=MessageUtil.messageToXml(textMessage);
				} else if (content.startsWith("����")) {
					String keyWord = content.replaceAll("����", "").trim();
					// ��ȡ�û����һ�η��͵ĵ���λ��
					UserLocation location = MySQLUtil.getLastLocation(request, fromUserName);
					// δ��ȡ��
					if (null == location) {
						respContent = getUsage();
						textMessage.setContent(respContent);
						respXml=MessageUtil.messageToXml(textMessage);
					} else {
						// ����ת���󣨾�ƫ�������������ܱ�POI
						List<BaiduPlace> placeList = BaiduMapUtil.searchPlace(keyWord, location.getBd09Lng(), location.getBd09Lat());
						// δ������POI
						if (null == placeList || 0 == placeList.size()) {
							respContent = String.format("/�ѹ��������͵�λ�ø���δ��������%s����Ϣ��", keyWord);
						} else {
							articleList = BaiduMapUtil.makeArticleList(placeList, location.getBd09Lng(), location.getBd09Lat());
							// �ظ�ͼ����Ϣ
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
				// �û����͵ľ�γ��
				String lng = requestMap.get("Location_Y");
				String lat = requestMap.get("Location_X");
				// ����ת����ľ�γ��
				String bd09Lng = null;
				String bd09Lat = null;
				// ���ýӿ�ת������
				UserLocation userLocation = BaiduMapUtil.convertCoord(lng, lat);
				if (null != userLocation) {
					bd09Lng = userLocation.getBd09Lng();
					bd09Lat = userLocation.getBd09Lat();
				}
				// �����û�����λ��
				MySQLUtil.saveUserLocation(request, fromUserName, lng, lat, bd09Lng, bd09Lat);

				StringBuffer buffer = new StringBuffer();
				buffer.append("[���]").append("�ɹ���������λ�ã�").append("\n\n");
				buffer.append("���������������ؼ��ʻ�ȡ�ܱ���Ϣ�ˣ����磺").append("\n");
				buffer.append("        ����ATM").append("\n");
				buffer.append("        ����KTV").append("\n");
				buffer.append("        �����Ƶ�").append("\n");
				buffer.append("�����ԡ������������ֿ�ͷ��");
				respContent = buffer.toString();
				textMessage.setContent(respContent);
				respXml=MessageUtil.messageToXml(textMessage);
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
					article1.setTitle("������ʢ�Ļ���ý��ӭ����");
					article1.setDescription("��ӭ��ע������ʢ�Ļ���ý����������˽������Ѷ��");
					article1.setPicUrl("http://mmbiz.qpic.cn/mmbiz/OGjXmcfnRdVibMj6WrTic8UGAu9AZMm2Mm2Q1vQfIakicbKBCENwGePHWlDrr0oe11uuFI9xfCzE329wndyA8bAqQ/0");
					article1.setUrl("http://mp.weixin.qq.com/s?__biz=MzAxMDAyODQzOA==&mid=200760002&idx=1&sn=0048218c75f50ec5eaa40cd51ba6add9#rd");
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
	/**
	 * Ĭ������ش�
	 * 
	 * @return
	 */
	private static String getDefaultAnswer() {
		String[] answer = { "Ҫ�������ĵ��ģ�", "�����㵽����˵ʲô�أ�", "û��������˵�ģ��ܷ񻻸�˵��",
				"��Ȼ�����������˼������ȴ������ȥ����", "������һͷ��ˮ�����µ�ֻ������Ԩ������Ĥ��~",
				"������Сѧ������������ʦ�̵ģ���������е����Ѱ�", "������仯̫�죬�����Ҳ����вţ�Ϊ����˵���Ҳ����ף�", };
		return answer[getRandomNumber(answer.length)];
	}
	/**
	 * ʹ��˵��
	 * 
	 * @return
	 */
	private static String getUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("�ܱ�����ʹ��˵��").append("\n\n");
		buffer.append("1�����͵���λ��").append("\n");
		buffer.append("������ڵײ��ġ�+����ť��ѡ��λ�á����㡰���͡�").append("\n\n");
		buffer.append("2��ָ���ؼ�������").append("\n");
		buffer.append("��ʽ������+�ؼ���\n���磺����ATM������KTV����������");
		return buffer.toString();
	}
	
	private static int getRandomNumber(int length) {
		Random random = new Random();
		return random.nextInt(length);
	}
}
