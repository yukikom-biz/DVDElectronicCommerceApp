DROP DATABASE IF EXISTS FifthFist;
DROP USER IF EXISTS 'fifthfist'@'localhost';
CREATE DATABASE FifthFist DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

CREATE USER 'fifthfist'@'localhost' IDENTIFIED by '12345678';
GRANT ALL ON FifthFist.* TO 'fifthfist'@'localhost';

USE FifthFist;

CREATE TABLE login (
                       id SERIAL,
                       username VARCHAR(255) NOT NULL,
                       password CHAR(128),
                       PRIMARY KEY (id)
);

CREATE TABLE items (
                    id SERIAL,
                    title VARCHAR(255) NOT NULL,
                    price INT NOT NULL,
                    players VARCHAR(255),
                    directors VARCHAR(255),
                    description TEXT,
                    updated DATETIME,
                    created DATETIME,
                    PRIMARY KEY (id)
);

CREATE TABLE orders (
                    id SERIAL,
                    name VARCHAR(255) NOT NULL,
                    postal CHAR(8) NOT NULL,
                    address VARCHAR(255) NOT NULL,
                    phone VARCHAR(255),
                    updated DATETIME,
                    created DATETIME,
                    PRIMARY KEY (id)
);

CREATE TABLE order_items (
                    id SERIAL,
                    order_id BIGINT UNSIGNED NOT NULL,
                    item_id BIGINT UNSIGNED NOT NULL,
                    price INT UNSIGNED NOT NULL,
                    quantity INT UNSIGNED NOT NULL,
                    updated DATETIME,
                    created DATETIME,
                    PRIMARY KEY (id),
                    FOREIGN KEY (item_id) REFERENCES items(id),
                    FOREIGN KEY (order_id) REFERENCES orders(id)
);

INSERT INTO login (username, password)
    VALUES ("admin","password"), ("user","12345");

INSERT INTO orders (id,name, postal, address, phone, updated, created)
    VALUES (1,"UserFirst UserLast", "11111111", "1 address", "2333232222", NOW(), NOW()),
           (2,"first1 last2", "39393939", "2 address", "222222222", NOW(), NOW());

INSERT INTO items (title, price, players, directors, description, updated, created)
    VALUES ("ショーシャンクの空に",1500,"ティム・ロビンス, モーガン・フリーマン, クランシー・ブラウン","フランク・ダラボン",
            "とある刑務所の受刑者が勝ち取り、分け与えた解放と救い－。誰の心にも静かに、爽やかな感動が訪れる…
            Rating PG-12 (C) 1994 Castle Rock Entertainment. (C) Dividen Productions/PeepShow Pictures. (C) 2004 Warner Bros. Entertainment Inc. All rights reserved.",
            NOW(),NOW()),

            ("ゴッド・ファーザー",1500,"マーロン・ブランド, アル・パチーノ, ジェームズ・カーン","フランシス・フォード・コッポラ",
             "コッポラ監督は、コルレオーネ一族の家族模様とファミリーが手がける恐ろしい組織犯罪の両方の間で巧みにストーリーを展開させながら、
             アメリカでの勢力争いを巡るシチリア人マフィアの血なまぐさい盛衰を描いた。
             (C)1972 Paramount ",
             NOW(),NOW()),

            ("ダークナイト",1500,"クリスチャン・ベール, マイケル・ケイン, ヒース・レジャー","クリストファー・ノーラン",
             "ゴッサム・シティーに、究極の悪が舞い降りた。ジョーカーと名乗り、不敵に笑うその男は、今日も銀行強盗の一味に紛れ込み、彼らを皆殺しにして、大金を奪った。
             しかし、それは彼が用意した悪のフルコースの、ほんの始まりに過ぎなかった…
             (C) 2008 Warner Bros. Entertainment Inc. All Rights Reserved. BATMAN and all related characters and elements are trademarks of and (C) DC Comics.",
             NOW(),NOW()),

            ("東京物語",2500,"小津安二郎","笠智衆, 東山千栄子, 原節子",
             "年老いた親が成長した子供たちを訪ねて親子の情愛を確認しあうという題材だが、小津の手にかかるとどうなるかを示す傑作。何気ない言動が教える各人の生活、思いがけない心情の吐露と発見。
             そして何事もなかったような人生の悲哀と深淵が見事に描かれている。
             尾道に住む老夫婦、周吉ととみが東京で暮らす子供達を訪れるために上京する。子供達は久しぶりの再会で二人を歓迎するが、それぞれ家庭の都合もあり、構ってばかりはいられない。
             結局、戦死した次男の嫁、紀子が二人の世話をすることになる。老夫婦は子供達がすっかり変わってしまったことに気づくのであった……。
             ラスト近く、ひとり残された夫が、静かに海を見つめているシーンが印象的。
             人間の孤独感、死生観といったテーマをとりこんだ味わい深い名作。(C)1953 松竹株式会社",NOW(),NOW()),

            ("七人の侍",2500,"三船敏郎, 志村喬, 稲葉義男","	黒澤明",
             "戦国時代、野武士達の襲撃に恐れおののく村があった。村人達はその対策として、用心棒として侍を雇う事にする。
             侍さがしは難航するが、才徳にすぐれた勘兵衛を始めとする個性豊かな七人の侍が決まった。最初は侍を恐れる村人達だったが、いつしか一致団結して戦いに挑むことに。
             しかし戦闘は熾烈を極めた・・・・・・。破格の製作費と年月をかけて作られた日本映画史上空前の超大作であり、世界に誇る日本映画の最高傑作。
             マルチ・カメラ方式の導入等による斬新で臨場感溢れる映像。加えて徹底した時代考証や緻密な脚本により、実際にあったかのような錯覚に陥らせる。
             迫力ある本作品は、年月を経た今も人々に感動を与え続けている。(C)1954 TOHO CO., LTD. ALL RIGHTS RESERVED.",
             NOW(),NOW()),

            ("浮雲",2500,"高峰秀子, 森雅之, 岡田茉莉子","	成瀬巳喜男",
             "1955年のキネマ旬報ベスト・テン第1位。林芙美子の同名小説を映画化した成瀬巳喜男＝高峰秀子の代表作で、世界の映画史に燦然と輝く名作中の名作。
             戦時中の占領地・インドシナで愛人関係にあった幸田ゆき子と農林技師の富岡。引き揚げ後も妻ある謙吾との縁が切れず、ゆき子は自活のため身を売る。
             小津安二郎の言葉俺にできないシャシンは溝口の「祇園の姉妹」と成瀬の「浮雲」だはあまりに有名 (C)1955 東宝",
             NOW(),NOW()),

            ("家族ゲーム",2500,"松田優作, 伊丹十三, 由紀さおり","森田芳光",
             "キネマ旬報ベストテン第1位作品。才人・森田芳光監督が、どこにでもある家庭の抱えている問題をユーモアに描いたシニカルでシュールなホームコメディ。
             高校受験を控える息子を持つ沼田家は、成績のパッとしない息子のために家庭教師をつける。だがやって来た吉本（松田優作）は三流大学の７年生という風変わりな男だった。
             (C)1983 日活／東宝",
             NOW(),NOW()),

            ("生きる",2500,"黒澤明","志村喬, 小田切みき, 小堀誠",
             "三十年間無欠勤の市役所の市民課長・渡辺勘治はある時、自分が癌に冒されている事を知る。暗い気分の勘治に息子夫婦の冷たい仕打ちが追い打ちをかける。
             街に出て羽目をはずすが気は晴れない。そこで事務員の小田切とよと出会い、今までの自分の仕事ぶりを反省する。勘治は心機一転、仕事に取り組むが・・・・・・。
             死に直面した公務員の生き方を通して、人間の真の生き甲斐を問いかける感動作。主人公を通して社会への批判も鋭く描かれていて考えさせられる。
             主人公を演じる志村喬は、鬼気迫る演技で名優としての地位を確立。夜更けの公園のブランコに乗って「ゴンドラの唄」を口ずさむシーンは、多くの人々に感銘を与えた。
             (C)1952 TOHO CO., LTD. ALL RIGHTS RESERVED.",
             NOW(),NOW()),

            ("天国と地獄",2500,"三船敏郎, 山崎努, 香川京子","黒澤明",
             "ナショナルシューズ重役・権藤の息子が何者かによって誘拐されるが、被害にあったのは実は運転手の子供だった。
             犯人は人違いをしていたのだ。犯人は疾走するこだま号に身代金を持って乗り込むよう要求してくるが、捜査陣は犯人の正体さえつかめない。
             そして事件は意外な展開を見せる・・・・・・。全編に渡って圧倒的な緊張感が溢れており、中でも日本映画史上に残るほど有名な、身代金奪取の意外なトリック・シーンが圧巻。
             他の犯罪映画とは一線を画したリアルなドラマ展開に、映画ファンのみならず世間が注目。日本映画では考えられないダイナミックさで、
             誘拐犯と捜査陣との息づまる対決を描くサスペンス映画の決定版。(C)1963 TOHO CO., LTD. ALL RIGHTS RESERVED.",
             NOW(),NOW()),

            ("君の名は。",2500,"神木隆之介, 上白石萌音, 成田凌","新海誠",
             "千年ぶりとなる彗星の来訪を一か月後に控えた日本。田舎町に暮らす女子高校生・三葉は憂鬱な毎日を過ごしていた。ある日、自分が男の子になる夢を見る。
             見覚えのない部屋、見知らぬ友人、目の前に広がるのは東京の街並み。都会での生活を満喫する三葉。一方、東京で暮らす男子高校生・瀧も奇妙な夢を見た。
             行ったこともない山奥の町で、自分が女子高校生になっているのだ。繰り返される不思議な夢、明らかに抜け落ちている記憶と時間。二人はお互いが入れ替わっていることに気付く。
             何度も入れ替わる事に戸惑いながらも、現実を少しずつ受け止める二人。残されたお互いのメモを通して、状況を乗り切っていく。
             しかし、気持ちが打ち解けてきた矢先、突然入れ替わりが途切れてしまう。自分たちが特別に繋がっていたことに気付いた瀧は、三葉に会いに行こうと決心。
             辿り着いた先には、意外な真実が待ち受けていた…。(C) 2016「君の名は。」製作委員会",
             NOW(),NOW()),

            ("アベンジャーズ",2500,"ロバート・ダウニー・Jr., クリス・エヴァンス, マーク・ラファロ","ジョス・ウェドン",
             "全世界興収10億ドル最速突破など、世界中で数々の新記録を樹立した話題のアクション超大作。 地球侵略へのカウントダウンが開始された時、70億人もの人類の未来は、“最強”の力を持つヒーローたちに託された。
             彼らの名は、“アベンジャーズ”。だが、意思に反して集結させられた彼らはそれぞれの心の傷に囚われ、ひとつのチームとして戦うことを拒み続ける。
             次第に明らかにされる“アベンジャーズ”の知られざる過去と苦悩…。人類史上最大の敵を前に、果たして彼らは地球を救うことができるのか？",
             NOW(),NOW());

INSERT INTO order_items (order_id, item_id, price, quantity, updated, created)
    VALUES (1,2,2323,2,NOW(),NOW()),
           (1,4,1313,4,NOW(),NOW()),
           (2,1,2222,3,NOW(),NOW());

