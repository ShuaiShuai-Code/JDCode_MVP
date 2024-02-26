package com.jd.common.constant;


/**
 * 全局常量保存在这里
 *
 * @author hao
 * @date 2018年11月08日16:54:40
 */
public class Constant {

  // =======全局变量的配置 start =============
  public static final String CQTVideoPlayDuration = "2";   //封闭式问题视频播放时长，单位秒

  public static final String OQTVideoRecDuration = "3";   //开放式问题视频录制时长，单位秒

  public static final String CQTVideoRecDuration = "5";    //封闭式问题视频录制时长，单位秒

  public static final String VIDEO_STORAGE_PATH = "liecompute_new";

  public static final String NATION_DATA = "nationality.json";


  //视频名称
  public static final String VIDEO_NAME_INTRO_FIRST = "Intro_First.mp4";
  public static final String VIDEO_NAME_INTRO_CLOSE = "Intro_Close.mp4";
  public static final String VIDEO_NAME_INTRO_OPEN = "Intro_Open.mp4";

  //静默视频的名称
  public static final String VIDEO_NAME_SILENCE_SHORT1 = "silence_short1.mp4";
  public static final String VIDEO_NAME_SILENCE_SHORT2 = "silence_short2.mp4";
  public static final String VIDEO_NAME_SILENCE_SHORT3 = "silence_short3.mp4";

  public static final String VIDEO_NAME_SILENCE_LONG1 = "silence_long1.mp4";
  public static final String VIDEO_NAME_SILENCE_LONG2 = "silence_long2.mp4";
  public static final String VIDEO_NAME_SILENCE_LONG3 = "silence_long3.mp4";


  public static final String INTRO_CATEGOARY_BAI = "BAI";
  public static final String INTRO_CATEGOARY_VA = "VA";
  public static final String INTRO_CATEGOARY_VAD = "VAD";


  public static final String JUMP_TYPE_CQT_CIT = "CQT_AND_CIT";


  public static final String ISLYING = "1";    //1 说谎
  public static final String NOTLYING = "0";    //0 没有说谎


  //服务器返回的错误码定义
  public static final String REP_CODE_OK = "000000";    //000000表示正常返回


  //用户类型字段
  public static final String USER_TYPE_TEST = "test";    // 测试数据
  public static final String USER_TYPE_BANK = "bank";    // 针对银行的测试数据
  public static final String USER_TYPE001 = "data1";    // 第1次采集的数据
  public static final String USER_TYPE002 = "data2";    // 第2次采集的数据
  public static final String USER_TYPE003 = "data3";    // 第3次采集的数据
  public static final String USER_TYPE004 = "data4";    // 第4次采集的数据
  public static final String USER_TYPE005 = "data5";    // 第5次采集的数据
  public static final String USER_TYPE006 = "data6";    // 第6次采集的数据


  public static final String USER_TYPE_SCHOOL = "school";    // 学校采集的数据
  public static final String USER_TYPE_HOMETOWN = "hometown";    // 家里采集的数据


  public static final String OS_TYPE_VIDEO_SOURCE = "android";    // 视频来源，移动端操作系统

  public static final String VIDEO_TYPE_CLOSE = "close";    // 问题类型  封闭式
  public static final String VIDEO_TYPE_OPEN = "open";    // 问题类型  开放式
  public static final String VIDEO_TYPE_FEEDBACK = "feedback";    // 问题类型  开放式


  public static final String ADD_USER_ERROR_USER_EXIST = "000002";    // 接口返回的错误代码


  public static final String SUBMIT_VERIFIED_CODE = "123";    // 提交验证码


  //视频录制参数
  //FFmpeg 码率控制
  public static final String FFMPEG_CRF_BITRATE = "22";  //控制视频的bitRate

  public static int FFMPEG_FRAME_RATE = 30;   //录制视频帧率，25 fps


  //视频上传超时的时间
  public static final long TIME_OUT_UPLOAD_VIDEO = 90;   //超时的时长， 单位秒


  //add by haoshuo  审讯版本的变量
  public static final String DATA_INPUT = "####INPUT";
  public static final String QUE_LIST_TOKEN = "####QLIST";
  public static final String QUE_LIST_FACE_DATA = "####FACEDATA";
  public static final String ADD_USER_FAILURE = "####ADDUSERFAILED";
  public static final String RESULT_JSON_STR = "####RESULT";
  public static final String JUMP_ACT = "####JUMPACT";
  public static final String CMD_CTRL = "####CMDCTRL";   //发送跳转的命令
  public static final long THREAD_SLEEP_TIME_MILI = 1000;


  public static int SHOW_TIMES_LOWTIME = 1000;
  public static int MAX_TIMES_STROOP = 40;   //一轮最多40次
  public static int MAX_TIME_SIMON_PER_TAIL = 5000;


  //提前预开启录像和语音识别的时间单位 毫秒
  //设置成50毫秒的时候会录制到前边的杂音，设置成零
  public static final int PRE_START_RECORD_CLOSE = 18;    // 预开启的时间设定，单位毫秒  预留50秒会有录制到视频的尾音
  public static final int PRE_START_RECORD_OPEN = 18;    // 预开启的时间设定，单位毫秒

  public static final int PRE_START_RECORD_CLOSE_ASR = 50;    // 封闭式问题，语音识别预开启的时间设定，单位毫秒


  //开放式问题页面，最后答题完成，显示获取报告页面，点击获取报告按钮时，用户等待的时长
  public static final long OPEN_QUESTION_PROCESS_TIME = 2;  //单位s
  public static final long OPEN_QUESTION_REQUEST_TIME = 0;  //单位s


  //错误提示信息定义
  public static final String ERROR_FROM_SERVER_001 = "JFS INNER ERROR";  // 视频上传至服务器的后台，后台没有成功上传至JFS,服务器内部错误


  public static final long MAX_VIDEO_LENGTH_APP = 60 * 1000;
  public static final long OPEN_VIDEO_MIN_ANS_TIME = 3;   //开放式问题的最短回答时间


  //app_key   设置key
  public static final String APP_ID = "15515064";
  public static final String APP_KEY = "hTb4waCcNqMgGRxD2yGGBwBa";
  public static final String SEC_KEY = "qjFcN7krwdEWB9DI8PVlVucWpAefY6wt";
  //字体
  public static final String SP_FontScale="字体大小调整";
  public static final String SP_position="刻度此";


  //语音合成测试接口
  public static final String APP_NAME = "call_out_mrcp";
  public static final String APP_AUTH_KEY = "ZkvY7HhZW2jsSKpcMgntuw==";
  public static final String BUSINESS_ID = "AUDIO-SDK";
  public static final String AUDIO_TYPE = "CONVERSATION";
  public static final String MODULE_TYPE_CQT = "CQT";
  public static final String MODULE_TYPE_CIT = "CIT";
  public static final String MODULE_TYPE_CIT_EDIT_CONTRAST = "CONTRAST";


  // SP 常量保存地址
  /**
   *
   */
  public static final String NUM_AND_NAME_DATA = "num_and_name_data";
  public static final String NUM_AND_NAME_JSON = "num_and_name_json";

  /**
   * 信息输入
   */
  public static final String INFO_INPUT = "info_input";


  /**
   * 答题页面的控制变量
   */
  //开始信息，发送给审讯端
  public static final String SIGNAL_START = "SIGNAL_START";
  //开始信息，发送给审讯端
  public static final String SIGNAL_USER_INFO = "SIGNAL_USER_INFO";
  public static final String SIGNAL_NAME_MAP = "SIGNAL_NAME_MAP"; //姓名map
  //答题页面tts播放完成
  public static final String SIGNAL_END_TTS_ONCE = "SIGNAL_END_TTS_ONCE";


  /**
   * 信息介绍页的话术
   */

  public static final String START_BAI = "<p>&ensp;&ensp; &ensp;&ensp;" +
      "接下来你将回答13个问题，如果你在这一过程中说谎，人工智能专家将作出准确的判断，这将对你产生非常不利的影响。所以，你需要详细、清晰、诚实地回答。</p>";

  public static final String START_VA = "\"<p>&ensp;&ensp; &ensp;&ensp;根据多年经验，说谎者更喜欢模糊回答，而说真话的人更喜欢提供可以核查的细节。" +
      "<br/>&ensp;&ensp; &ensp;&ensp;我将仔细检查你提供的细节在多大程度上可以得到验证。<br/>&ensp;&ensp; &ensp;&ensp;你所说的事情应尽量" +
      "包括人证、物证，聊天记录、消费记录、录像等。<br/>&ensp;&ensp; &ensp;&ensp;举个例子，比如：<br/><font  color=\\\"red\\\">问：" +
      "“这件事是你干的吗？”</font><br/><font  color=\\\"red\\\">答：“不是啊，当时我在京东总部的麦当劳餐厅吃饭，和李四在一起，李四他可以作证，" +
      "我还有那儿的消费记录，餐厅也留有监控录像，能够证明那天晚上八点之前，我一直在那。”</font></p>\"";

  public static final String START_GUESS_NUM = "<p>&ensp;&ensp; &ensp;&ensp;下面我将探测你内心的数字，步骤如下：" +


      "<br/>&ensp;&ensp; &ensp;&ensp;1． 你从袋子里摸出一个数字并记住数值，然后放回去，不要让其他人看见" +
      "<br/>&ensp;&ensp; &ensp;&ensp;2． 你在纸条上写上这个数字，翻过来压住，不要让其他人看见 " +
      "<br/>&ensp;&ensp; &ensp;&ensp;3．  将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势 " +

      "</p>";

  public static final String START_GUESS_NAME = "<p>&ensp;&ensp; &ensp;&ensp;下面我将探测你或者家人的姓名，步骤如下：" +


      "<br/>&ensp;&ensp; &ensp;&ensp;1.请你在屏幕上输入五个姓名，其中一个是你或者家人的姓名，其他四个是你编造的姓名。" +
      "<br/>&ensp;&ensp; &ensp;&ensp;2.将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势。 " +

      "</p>";

  public static final String INTRO_TEXT_GUESS_NUM = "下面我将探测你内心的数字，步骤如下：" +
      "1．  你从袋子里摸出一个数字并记住数值，然后放回去，不要让其他人看见       " +
      "2． 你在纸条上写上这个数字，翻过来压住，不要让其他人看见        " +
      "3． 将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势       " +
      "";

  public static final String INTRO_TEXT_GUESS_NAME = "下面我将探测你或者家人的姓名，步骤如下：    " +
      "1  请你在屏幕上输入五个姓名，其中一个是你或者家人的姓名，其他四个是你编造的姓名。     " +
      "2  将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势 " +
      "";


  public static final String GUESS_NUM_TIPS_START_SHOW = "接下来我将会问你几个简单的问题，你全部只需回答“不是” 。平复一下心情，我们即将开始。";
  public static final String GUESS_NUM_TIPS_START_READ = "接下来我将会问你几个简单的问题，你全部只需回答“不是” 。       " +
      "问题之间间隔10秒钟左右，测试过程中尽量保持身体不动。" +
      "平复一下心情，我们即将开始。";

  public static final String GUESS_NAME_TIPS_START_SHOW = "接下来我将会问你几个简单的问题，你全部只需回答“不是”。平复一下心情，我们即将开始。";
  public static final String GUESS_NAME_TIPS_START_READ = "接下来我将会问你几个简单的问题，你全部只需回答“不是” 。 " +
      "问题之间间隔十秒钟左右，测试过程中尽量保持身体不动。" +
      "平复一下心情，我们即将开始。";


  public static final String GUESS_NUM_FINISH_READ = "好的，测试结束，你可以把手拿出来了。";
  public static final String GUESS_NUM_FINISH_TEXT = "测试结束";


  public static final String DEVICE_NAME = "NBee_SPP";
  public static final String INPUT_GENDER_MALE = "male";
  public static final String DATA_USER_REALITY = "##DATA_USER_REALITY##";
  public static final String INPUT_GENDER_FEMALE = "female";


  /**
   * 判断下一题的逻辑， 11 ,19 ,10  ,8
   */
  public static final long NEXT_Q_WAIT_TIME = 11;
  public static final long NEXT_Q_JUDGE_TIME = 19;
  public static final long NEXT_PRE_WAIT_TIME = 10;
  public static final long NEXT_PEAK_SPAN = 8;
  public static final long SHOW_ALERT_FLAG_WAIT_TIME = 2;

  public static final long INTRO_NEXT_PEAK_SPAN = 4;

  public static final boolean IS_FIRST_TWO = false;


  public static final String SELECT_DATA = "select_data";
  /*CIT介绍话术*/
  public static final String INTRO_CIT_SHOW = "<p>" +


      "<br/>&ensp;&ensp; &ensp;&ensp;1． 将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势。" +
      "<br/>&ensp;&ensp; &ensp;&ensp;2． 接下来我将会问你几个简单的问题，你全部只需回答“不是” 。平复一下心情，我们即将开始。 " +

      "</p>";


  public static final String INTRO_CIT_READ = "" +
      "1．  将你的任意一只手伸到测谎机器人里边，另一只胳膊也放在桌上，调整到最舒服的姿势。       " +
      "2．  接下来我将会问你几个简单的问题，你全部只需回答“不是” 。平复一下心情，我们即将开始。        " +
      "";


  public static final String ADD_PROBLEM = "add_problem";

  public static final String FINISH_TRIAL_CIT_CATEGORY = "finish_trial_cit_category";

  public static final String CIT_PROBLEM_DATA = "cit_problem_data";
  public static final String CIT_UPLOAD_DATA = "cit_upload_data";


  /**
   * 猜数字页面
   */

  public static final String GUESS_NUM_MAP = "num_map";
  public static final String GUESS_NUM_MAP_DATA = "numMapdata";

  //自动生成用户的编号
  public static final String GUESS_GENERATE_USER_ID = "GUESS_GENERATE_USER_ID";
  //自动生成dimension id
  public static final String GUESS_GENERATE_DIMENSION_ID = "GUESS_GENERATE_DIMENSION_ID";


  /**
   * CIT编辑页面
   */
  public static final String DIALOG_TITLE = "DIALOG_TITLE";
  public static final String DIALOG_TEXT = "DIALOG_TEXT";
  public static final String DIALOG_CONFIRM = "DIALOG_CONFIRM";
  public static final String DIALOG_CANCEL = "DIALOG_CANCEL";


  public static final String IP = "ip";

  public static final String TOKEN = "token";


}
