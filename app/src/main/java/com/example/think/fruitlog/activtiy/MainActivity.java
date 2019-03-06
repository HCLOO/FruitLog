package com.example.think.fruitlog.activtiy;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.think.fruitlog.R;
import com.example.think.fruitlog.adapter.MyAdapter;
import com.example.think.fruitlog.model.Fruit;
import com.example.think.fruitlog.util.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView iconImage,sexImage;
    TextView TVUsername,TVEmail;
    Fruit[] fruitArray=new Fruit[20];
    List<Fruit> fruitList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initArray();
        initFruit();
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        iconImage=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.icon_image);
        TVUsername=(TextView) navigationView.getHeaderView(0).findViewById(R.id.username);
        TVEmail=(TextView) navigationView.getHeaderView(0).findViewById(R.id.email);
        sexImage=(ImageView) navigationView.getHeaderView(0).findViewById(R.id.iv_sex);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);//可为HomeAsUp按钮设置自定义图标
        }
        if (SharedPreferencesUtil.getPreferenceSex().equals("女")) {
            iconImage.setBackgroundResource(R.mipmap.touxiang2);
            sexImage.setBackgroundResource(R.mipmap.woman_img);
        } else {
            iconImage.setBackgroundResource(R.mipmap.touxiang1);
            sexImage.setBackgroundResource(R.mipmap.man_img);
        }
        TVUsername.setText(SharedPreferencesUtil.getPreferenceUsername());
        TVEmail.setText(SharedPreferencesUtil.getPreferenceEmail());

        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        final MyAdapter adapter=new MyAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initFruit();
                                adapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();
            }
        });

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.float_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"欢迎使用FruitLog，FruitLog为你而生！",Snackbar.LENGTH_SHORT)
                        .setAction("点评", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(MainActivity.this,PraiseActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        navigationView.setCheckedItem(R.id.buy);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.buy:
                        Intent intent=new Intent(MainActivity.this,BuyActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.explain:
                        AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("产品说明");
                        alertDialog.setMessage("FruitLog软件为HCLOO原创作品，汇集各自品类的水果及其特性，专为喜爱水果的果友量身打造！"
                                +"\n"+"\n"+"若想了解FruitLog或HCLOO的其他作品，请点击浏览HCLOO的Github主页！");
                        alertDialog.setCancelable(true);
                        alertDialog.setPositiveButton("点击浏览", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Uri uri=Uri.parse("https://github.com/HCLOO");
                                Intent intent1=new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(intent1);
                            }
                        });
                        alertDialog.setNegativeButton("返回", null);
                        alertDialog.show();
                        break;
                    case R.id.location:
                        Intent intent1=new Intent(MainActivity.this,DaiduMapActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.mail:
                        Intent intent2=new Intent(MainActivity.this,LogTagActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.praise_log:
                        Intent intent3=new Intent(MainActivity.this,PraiseLogActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.buy_log:
                        Intent intent4=new Intent(MainActivity.this,BuyLogActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.news:
                        Intent intent5=new Intent(MainActivity.this,NewsActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.comment_location:
                        Intent intent6=new Intent(MainActivity.this,CommentActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.fruit_moments:
                        Intent intent7=new Intent(MainActivity.this,FruitMomentsActivity.class);
                        startActivity(intent7);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return true;
    }

    public void initArray() {
        int i=0;
        Fruit fruit;
        fruit=new Fruit(R.mipmap.apple1,"Apple","苹果（学名：Malus pumila）是水果的一种，" +
                "是蔷薇科苹果亚科苹果属植物，其树为落叶乔木。苹果的果实富含矿物质和维生素，是人们经常食用的水果之一。"
                +"\n"+"\n"+"营养元素：苹果的性味温和，含有丰富的碳水化合物、维生素和微量元素，有糖类、有机酸、果" +
                "胶、蛋白质、钙、磷、钾、铁、维生素A、维生素B、维生素C和膳食纤维，另含有苹果酸，酒石酸，胡萝卜素" +
                "，是所有蔬果中营养价值最接近的一个。" +
                "\n"+"\n"+"功效：降低胆固醇、防癌抗癌、呼吸管道清理剂、促进胃肠蠕动、" +
                "维持酸碱平衡、既能减肥，又可使皮肤润滑柔嫩。");
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.snowpear1,"Snow Pear","雪梨，梨名，肉嫩白如雪，故称，是一种常见的水果。"
                +"\n"+"\n"+"营养元素：味甘性寒，含苹果酸、柠檬酸、维生素B1、B2.C、胡萝卜素等。" +
                "\n"+"\n"+"功效：有降低血压和养阴清热的效果、具生津润燥、清热化痰、养血生肌之功效、治风热、润肺、凉心、消痰、" +
                "降火、解毒、对咽喉干、痒、痛、音哑、痰稠、便秘、尿赤、祛痰均有良效。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.peach1,"Peach","桃子（学名：Amygdalus persica L.）：蔷薇科、桃属植物。近球形核果，表面有毛茸，" +
                "肉质可食，为橙黄色泛红色，直径7.5厘米，有带深麻点和沟纹的核，内含白色种子。"
                +"\n"+"\n"+"营养元素：桃肉含蛋白质、脂肪、碳水化合物、粗纤维、钙、磷、铁、胡萝卜素、维生素B1、以及有机酸（主要是苹果酸和" +
                "柠檬酸）、糖分（主要是葡萄糖、果糖、蔗糖、木糖）和挥发油。" +
                "\n"+"\n"+"功效：适宜低血钾和缺铁性贫血患者食用、桃子适宜低血糖者以及口干饥渴之时食用、适宜低血钾和缺铁性贫血者食用、" +
                "适宜肺病、肝病、水肿患者食用、适宜胃纳欠香、消化力弱者食用、有破血、和血、益气之效。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.banana1,"Banana","香蕉（学名：Musa nana Lour.）芭蕉科芭蕉属植物，又指其果实。热带地区广泛栽培食用。" +
                "香蕉味香、富含营养，终年可收获，在温带地区也很受重视。植株为大型草本，从根状茎发出，由叶鞘下部形成高3～6公尺(10～20尺)的假杆；叶长圆形" +
                "至椭圆形，有的长达3～3.5公尺(10～11.5尺)，宽65公分(26寸)，10～20枚簇生茎顶。穗状花序下垂，由假杆顶端抽出，花多数，淡黄色；果序" +
                "弯垂，结果10～20串，约50～150个。植株结果后枯死，由根状茎长出的吸根继续繁殖，每一根株可活多年。"
                +"\n"+"\n"+"营养元素：香蕉含有称为“智慧之盐”的磷，又有丰富的蛋白质、糖、钾、维生素A和C，同时纤维也多，堪称相当好的营养食品。香蕉果肉每100克含糖" +
                "15%以上，酸0.2～0.3%，蛋白质1.5%，还有丰富的磷53毫克、钙19毫克、钾400毫克、维生素C24毫克。香蕉还含有果胶、多种酶类物质以及微量元素等。" +
                "\n"+"\n"+"功效：增强对疾病的抵抗力、抗脚气病，促进食欲、助消化、保护神经系统、促进人体正常生长和发育、防止血压上升及肌肉痉挛、具有消除" +
                "疲劳的效果、能减轻心理压力、解除忧郁、维持正常的生殖力和视力、促进肠胃蠕动、润肠通便、润肺止咳、清热解毒、助消化和滋补的作用。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.grape1,"Grape","葡萄（学名：Vitis vinifera L.）为葡萄科葡萄属木质藤本植物，小枝圆柱形，有纵棱纹，无毛或被稀疏" +
                "柔毛，叶卵圆形，圆锥花序密集或疏散，基部分枝发达，果实球形或椭圆形，花期4-5月，果期8-9月。"
                +"\n"+"\n"+"营养元素：葡萄中含有矿物质钙、钾、磷、铁以及多种维生素B1、维生素B2、维生素B6、维生素C和维生素P等，还含有多种人体所需的氨基酸。" +
                "\n"+"\n"+"功效：葡萄性平、味甘酸，入肺、脾、肾经、有补气血、益肝肾、生津液、强筋骨、止咳除烦、补益气血、通利小便的功效、降低人体血清胆固醇水平、" +
                "降低血小板的凝聚力、对预防心脑血管病有一定作用、对心脏有保护作用。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.pitaya1,"Pitaya","火龙果（学名：Hylocereus undatus 'Foo-Lon'）是仙人掌科、量天尺属量天尺的栽培品种，攀援肉质灌木，" +
                "具气根。分枝多数，延伸，叶片棱常翅状，边缘波状或圆齿状，深绿色至淡蓝绿色，骨质；花漏斗状，于夜间开放；鳞片卵状披针形至披针形，萼状花被片黄绿色，线形" +
                "至线状披针形，瓣状花被片白色，长圆状倒披针形，花丝黄白色，花柱黄白色，浆果红色，长球形，果脐小，果肉白色、红色。种子倒卵形，黑色，种脐小。7-12月开花" +
                "结果。"
                +"\n"+"\n"+"营养元素：火龙果性甘平，主要营养成分有蛋白质、膳食纤维、维生素B2、维生素B3、维生素C、铁、磷、钙、镁、钾等。富含大量果肉纤维，有丰富的胡萝卜素，" +
                "维他命B1、B2、B3、B12、C等，果核内 (黑色芝麻之种子) 更含有丰富的钙、磷、铁等矿物质及各种酶、白蛋白、纤维质及高浓度天然色素花青素(尤以红肉为最)。" +
                "\n"+"\n"+"功效：有效防止血管硬化、可阻止心脏病发作和血凝块形成引起的脑中风、对抗自由基，有效抗衰老、提高对脑细胞变性的预防，抑制痴呆症的发生。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.cherrytomatoes1,"Cherry Tomatoes","圣女果，常被称为小西红柿，中文正式名称为樱桃番茄，是一年生草本植物，属茄科番茄属。" +
                "植株最高时能长到2米。果实鲜艳，有红、黄、绿等果色，单果重一般为10～30克，果实以圆球型为主；种子比普通番茄小，心形。密被茸毛，百粒重1.2～1.5千克。" +
                "果实直径约1～3厘米，鲜红色。"
                +"\n"+"\n"+"营养元素：番茄干物质含量为7.7％，其中糖分占1.8～5％，酸0.15％～0.75％、蛋白质0.7%～1.3%、维生素0.6％～1.4％、矿物质0.5～0.8％、果胶1.3％～2.5%、" +
                "维生素C25～45毫克／100克。此外，还有胡箩卜素、维生素Bl、B2、番茄色素等。" +
                "\n"+"\n"+"功效：圣女果具有生津止渴、健胃消食、清热解毒、凉血平肝、补血养血和增进食欲的功效、可治口渴，食欲不振。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.kiwifruit1,"Kiwifruit","猕猴桃（学名：Actinidia chinensis Planch），也称奇异果（奇异果是猕猴桃的一个人工选育品种" +
                "，因使用广泛而成为了猕猴桃的代称）。"
                +"\n"+"\n"+"营养元素：它含有丰富的维生素C、维生素A、维生素E以及钾、镁、纤维素之外，还含有其他水果比较少见的营养成分——叶酸、胡萝卜素、钙、黄体素、氨基酸、" +
                "天然肌醇。猕猴桃的的营养价值远超过其他水果，它的钙含量是葡萄柚的2.6倍、苹果的17倍、香蕉的4倍，维生素C的含量是柳橙的2倍。" +
                "\n"+"\n"+"功效：起到扩张血管和降低血压的作用、加强心脏肌肉、稳定血液中胆固醇的水平、具有抗糖尿病的潜力、治疗腹泻和痢疾、提高总蛋白质水平、解决胃虚弱问题" +
                "、有效治疗呼吸问题、改善视力、消除皱纹和细纹、提升免疫功能、治疗肝脏疾病、消化不良、贫血、泌尿系统问题、呼吸系统疾病、脑疾病等、它还增加红血球生产、" +
                "加强牙齿和指甲。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.strawberry1,"Strawberry","草莓（学名：Fragaria × ananassa Duch.），多年生草本植物。高10-40厘米，茎低于叶或近相等，" +
                "密被开展黄色柔毛。叶三出，小叶具短柄，质地较厚，倒卵形或菱形，上面深绿色，几无毛，下面淡白绿色，疏生毛，沿脉较密；叶柄密被开展黄色柔毛。聚伞花序，花" +
                "序下面具一短柄的小叶；花两性；萼片卵形，比副萼片稍长；花瓣白色，近圆形或倒卵椭圆形。聚合果大，宿存萼片直立，紧贴于果实；瘦果尖卵形，光滑。花期4-5月，" +
                "果期6-7月。"
                +"\n"+"\n"+"营养元素：含有丰富的维生素C、维生素A、维生素E、维生素PP、维生素B1、维生素B2、胡萝卜素、鞣酸、天冬氨酸、铜、草莓胺、果胶、纤维素、叶酸、铁、钙、" +
                "鞣花酸与花青素等营养物质。" +
                "\n"+"\n"+"功效：可缓解夜盲症、具有维护上皮组织健康、明目养肝、促进生长发育之效、促进胃肠道的蠕动、促进胃肠道内的食物消化、改善便秘、预防痤疮、肠癌的发生。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.mango1,"Mango","芒果是杧果（中国植物志）的通俗名（拉丁学名：Mangifera indica L.），芒果是一种原产印度的漆树科" +
                "常绿大乔木，叶革质，互生；花小，杂性，黄色或淡黄色，成顶生的圆锥花序。核果大，压扁，长5-10厘米，宽3-4.5厘米，成熟时黄色，味甜，果核坚硬。"
                +"\n"+"\n"+"营养元素：含有丰富的糖、维生素，蛋白质0.65%-1.31%，每100克果肉含胡萝卜素2281-6304微克，可溶性固形物14%-24.8%，而且人体必需的微量元素" +
                "〈硒、钙、磷、钾、铁等〉含量也很高。" +
                "\n"+"\n"+"功效：具有清肠胃的功效、对于晕车、晕船有一定的止吐作用、具有防癌、抗癌的作用、起到滋润肌肤的作用、防治高血压、动脉硬化、防治便秘、" +
                "具有抑制流感病毒的作用。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.hamimelon1,"Hami Melon","哈密瓜（Cucumis melo var. saccharinus），是甜瓜的一个转变。又名雪瓜、贡瓜，是一类" +
                "优良甜瓜品种，果型圆形或卵圆形，出产于新疆。味甜，果实大，以哈密所产最为著名，故称为哈密瓜。因为味道甜美。据史料记载，清朝康熙年间，哈密王把甜" +
                "瓜作为礼品向朝廷进贡，“哈密瓜”便由此得名，并成为新疆甜瓜的总称。"
                +"\n"+"\n"+"营养元素：哈密瓜的干物质中，含有4.6%-15.8%的糖分，纤维素2.6%-6.7%， 还有苹果酸、果胶物质、维生素A、B、C，尼克酸以及钙、磷、铁等元素。" +
                "其中铁的含量比鸡肉多两三倍，比牛奶高17倍。" +
                "\n"+"\n"+"功效：具有疗饥、利便、益气、清肺热止咳的功效、适宜于肾病、胃病、咳嗽痰喘、贫血和便秘患者、美容防晒、确保机体保持正常新陈代谢" +
                "、保持正常的心率和血压、可以有效的预防冠心病、防止肌肉痉挛。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.orange1,"Orange","橘（英语：Mandarin orange；学名：Citrus reticulata）是芸香科柑橘属的一种水果。" +
                "“橘”（jú）和“桔”（jú）都是现代汉语规范字，然“桔”作橘子一义时，为“橘”的俗写。在广东的一些方言中二字同音，“桔”也曾做过“" +
                "橘”的二简字。闽南语称橘为柑仔。西南官话区的各方言中呼为“柑子”或“柑儿”。"
                +"\n"+"\n"+"营养元素：橘子的可食用部分大约为76%。每100g中含大约能量176kj、水分88.2g、蛋白质0. 8g、脂肪0. 4g、膳食纤维1.4g，碳水化合物8.9g、" +
                "胡萝卜素16605mg、视黄醇当量2775mg，硫胺素0.05mg、核黄素0.04mg、尼克酸0.2mg;维生素C 19mg、维生素E 0.45mg;钾177mg、钠1.3mg，" +
                "钙19mg、镁16mg、铁0.2mg、锰0.05mg、锌0.1mg、铜0.07mg，磷18mg、硒0.455g。尚含维护血管弹性的橙皮甙、柠檬酸等物质。" +
                "\n"+"\n"+"功效：具有润肺、止咳、化痰和止渴的功效、通络化痰、顺气活血的功效、常用于治疗痰滞咳嗽等症、有效防治高血压、有散结、理气止痛的功效" +
                "具有开胃理气，止渴润肺的功效、治胸隔结气、呕逆少食、胃阴不足、口中干渴、肺热咳嗽及饮酒过度、具有理气燥湿、化痰止咳、健脾和胃的功效、预防冠心病和动脉硬化。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.litchi1,"Litchi","荔枝营养丰富，含葡萄糖、蔗糖、蛋白质、脂肪以及维生素A、B、C等，并含叶酸、精氨酸、色氨酸等各种营养素。"
                +"\n"+"\n"+"营养元素：橘子的可食用部分大约为76%。每100g中含大约能量176kj、水分88.2g、蛋白质0. 8g、脂肪0. 4g、膳食纤维1.4g，碳水化合物8.9g、" +
                "胡萝卜素16605mg、视黄醇当量2775mg，硫胺素0.05mg、核黄素0.04mg、尼克酸0.2mg;维生素C 19mg、维生素E 0.45mg;钾177mg、钠1.3mg，" +
                "钙19mg、镁16mg、铁0.2mg、锰0.05mg、锌0.1mg、铜0.07mg，磷18mg、硒0.455g。尚含维护血管弹性的橙皮甙、柠檬酸等物质。" +
                "\n"+"\n"+"功效：具有健脾生津，理气止痛之功效、适用于身体虚弱，病后津液不足，胃寒疼痛，疝气疼痛等症、改善失眠、健忘、多梦等症、促进皮肤新陈代谢，延缓衰老。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.cherry1,"Cherry","樱桃（学名：Cerasus pseudocerasus），是某些李属类植物的统称，包括樱桃亚属、酸樱桃亚属、" +
                "桂樱亚属等。乔木，高2-6米，树皮灰白色。小枝灰褐色，嫩枝绿色，无毛或被疏柔毛。冬芽卵形，无毛。" +
                "果实可以作为水果食用，外表色泽鲜艳、晶莹美丽、红如玛瑙，黄如凝脂。"
                +"\n"+"\n"+"营养元素：维生素A含量比葡萄、苹果、橘子多4-5倍。胡萝卜素含量比葡萄、苹果、橘子多4～5倍。此外，樱桃中还含有维生素B、C及钙、磷等矿物元素。" +
                "每100g含水分83g，蛋白质1.4g，脂肪0.3g，糖8g，碳水化合物14.4g，热量66千卡，粗纤维0.4g，灰分0.5g，钙18mg，磷18mg，铁5.9mg，胡萝卜素0.15mg，" +
                "硫胺素0.04mg，核黄素0.08mg，尼可酸0.4mg，抗坏血酸900mg，钾258mg，钠0.7mg，镁10.6mg。" +
                "\n"+"\n"+"功效：缓解贫血、调中益气、健脾和胃、祛风湿、对食欲不振、消化不良、风湿身痛等均有益处、养颜驻容，使皮肤红润嫩白，去皱消斑、" +
                "消化不良者、瘫痪、风湿腰腿痛者、体质虚弱、面色无华者适宜食用。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.durian1,"Durian","榴莲（学名：Durio zibethinus Murr ），是一种巨型的热带常绿乔木，叶片长圆，顶端较尖，" +
                "聚伞花序，花色淡黄，果实足球大小，果皮坚实，密生三角形刺，果肉是由假种皮的肉包组成，肉色淡黄，粘性多汁是一种极具经济价值的水果。"
                +"\n"+"\n"+"营养元素：它含有很高的糖份，并且含淀粉11%，糖分13%，蛋白质3%，还有多种维生素，脂肪，钙，铁和磷等。" +
                "\n"+"\n"+"功效：滋阴壮阳、增强免疫力、治疗痛经、开胃促食欲、通便治便秘、预防和治疗高血压、防癌抗癌。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.watermelon1,"Watermelon","西瓜（学名：Citrullus lanatus (Thunb.) Matsum. et Nakai）一年生蔓生藤本；" +
                "茎、枝粗壮，具明显的棱。卷须较粗壮，具短柔毛，叶柄粗，密被柔毛；叶片纸质，轮廓三角状卵形，带白绿色，两面具短硬毛，叶片基部心形。雌雄同株。" +
                "雌、雄花均单生于叶腋。雄花花梗长3-4厘米，密被黄褐色长柔毛；花萼筒宽钟形；花冠淡黄色；雄蕊近离生，花丝短，药室折曲。雌花：花萼和花冠与雄花同；" +
                "子房卵形，柱头肾形。果实大型，近于球形或椭圆形，肉质，多汁，果皮光滑，色泽及纹饰各式。种子多数，卵形，黑色、红色，两面平滑，基部钝圆，" +
                "通常边缘稍拱起，花果期夏季。"
                +"\n"+"\n"+"营养元素：西瓜果肉：蛋白质、葡萄糖、蔗糖、果糖、苹果酸、瓜氨酸、谷氨酸、精氨酸、磷酸、内氨酸、丙酸、乙二醇、甜菜碱、腺嘌呤、蔗糖、萝卜素、" +
                "胡萝卜素、番茄烃、六氢番茄烃、维他命A、B、C、挥发性成分中含多种醛类。\n" + "西瓜种子：含脂肪油、蛋白质、维生素B2、淀粉、戊聚糖、丙酸、尿素、蔗糖等。\n" +
                "西瓜皮：西瓜皮富含维生素C、E。" +
                "\n"+"\n"+"功效：清热生津，解渴除烦、有益健康和美容、利尿、增加皮肤弹性，减少皱纹，增添光泽、美腿、使头发秀美稠密。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.pineapple1,"Pineapple","菠萝（学名：Ananas comosus），是热带水果之一。福建和台湾地区称之为旺梨或者旺来" +
                "（ông-lâi），新马一带称为黄梨，大陆及香港称作菠萝。有70多个品种，岭南四大名果之一。"
                +"\n"+"\n"+"营养元素：含有大量的果糖，葡萄糖，维生素B、C，磷，柠檬酸和蛋白酶等物质。每100克菠萝含水分87.1克，蛋白质0.5克，脂肪0.1克，纤维1.2克，尼克酸0.1毫克，" +
                "钾126毫克，钠1.2毫克，锌0.08毫克，碳水化合物8.5克，钙20毫克，磷6毫克，铁0.2毫克，胡萝卜素0.08毫克，硫胺素0.03毫克，核黄素0.02毫克，" +
                "维生素C8~30毫克，灰分0.3克，另含多种有机酸及菠萝酶等。" +
                "\n"+"\n"+"功效：具有清暑解渴、消食止泻、补脾胃、固元气、益气血、消食、祛湿、养颜瘦身等功效、帮助消化、促进血循环、利尿、对肾炎和高血压者有益、有降温的作用" +
                "、对支气管炎有辅助疗效、缓解便秘、有一定的抗癌效果。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.carambola1,"Carambola","阳桃（学名：Averrhoa carambola L.），别名：五敛子，杨桃，洋桃，三廉子等，被子植物门，五敛子属。" +
                "阳桃是一种产于热带亚热带的水果，具有非常高的营养价值。"
                +"\n"+"\n"+"营养元素：每100克果肉含水分92.5，蛋白质0.6克，脂肪0.1克，纤维1.1克，碳水化合物5.3克，灰分0.4克，胡萝卜素20微克，硫胺素0.01毫克，核黄素0.05毫克，" +
                "尼克酸1.0毫克，抗坏血酸7毫克，钾126毫克，钠0.7毫克，钙3毫克，镁6毫克，铁0.6毫克，锌0.5毫克，磷27毫克，硒0.84微克。" +
                "\n"+"\n"+"功效：增强机体的抗病能力、利小便，解酒毒，生津止渴、和中消食、清热利咽。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.passionfruit1,"Passion Fruit","鸡蛋果，中药材名，又名百香果。本品为西香连科植物鸡蛋果的果实。拉丁植物名：Passiflora edulis sims采收" +
                "和储藏：用实生苗栽培2年后结果，分株苗定植的当年能结果。8-11月当果皮 紫色时即成熟，应分批采收。鲜用或晒干。"
                +"\n"+"\n"+"营养元素：果实含果胶（pectin)1%，果胶中的主成分为82.02%的半乳糖醛酸（galacturonic acid)和7.9%的甲基硝类化合物。" +
                "\n"+"\n"+"功效：清肺润燥、安神止痛、和血止痢、主咳嗽、咽干、声嘶、大便秘结、失眼、痛经、关节痛、痢疾。");
        ++i;
        fruitArray[i]=fruit;
        fruit=new Fruit(R.mipmap.papaya1,"Papaya","木瓜（学名：Chaenomeles sinensis (Thouin)Koehne）：蔷薇科木瓜属，灌木或小乔木，高达5－10米，" +
                "叶片椭圆卵形或椭圆长圆形，稀倒卵形，长5－8厘米，宽3.5－5.5厘米，叶柄长5－10毫米，微被柔毛，有腺齿；果实长椭圆形，长10－15厘米，暗黄色，木质，味芳香，果梗短。花期4月，果期9－10月。"
                +"\n"+"\n"+"营养元素：果实营养丰富，富含维生素。木瓜中酸类成分包括苹果酸、枸橼酸、酒石酸等。" +
                "\n"+"\n"+"功效：具有美容功效、补偿胰和肠道的分泌，补充胃液的不足、帮助机体修复组织、消除有毒物质、增强人体免疫力、帮助机体抵抗包括甲流在内的病毒侵袭。");
        ++i;
        fruitArray[i]=fruit;
    }

    public void initFruit() {
        if(fruitList!=null)
            fruitList.clear();
        Random rand = new Random();
        boolean[] bool = new boolean[fruitArray.length];
        int index;
        for(int i=0;i<fruitArray.length;++i) {
            do {
                index = rand.nextInt(fruitArray.length);
            } while (bool[index]);
            bool[index] = true;
            fruitList.add(fruitArray[index]);
        }
    }
}
