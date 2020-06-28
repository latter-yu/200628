import java.util.Scanner;

public class Test {

    public static void main1(String[] args) {

        // 小易经常沉迷于网络游戏.
        // 有一次, 他在玩一个打怪升级的游戏, 他的角色的初始能力值为 a.
        // 在接下来的一段时间内, 他将会依次遇见 n 个怪物, 每个怪物的防御力为 b1, b2, b3 ... bn.
        // 如果遇到的怪物防御力 bi 小于等于小易的当前能力值 c, 那么他就能轻松打败怪物, 并且使得自己的能力值增加 bi;
        // 如果 bi 大于 c, 那他也能打败怪物, 但他的能力值只能增加 bi 与 c 的最大公约数.
        // 那么问题来了,在一系列的锻炼后,小易的最终能力值为多少?

        // 输入描述:
        // 对于每组数据, 第一行是两个整数 n(1 ≤ n < 100000) 表示怪物的数量和 a 表示小易的初始能力值.
        // 第二行 n 个整数, b1, b2 ... bn(1 ≤ bi ≤ n) 表示每个怪物的防御力
        // 输出描述:
        // 对于每组数据, 输出一行.
        // 每行仅包含一个整数, 表示小易的最终能力值
        // 示例:
        // 输入
        // 3 50
        // 50 105 200
        // 5 20
        // 30 20 15 40 100
        // 输出
        // 110<br/>205<br/>
        // ps: <br/> 是换行.

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt(); // 怪物数量
            int a= in.nextInt(); // 小易初始能量值
            int[] b = new int[n]; // 存放每一个怪物防御力
            for (int i = 0; i < n; i++) {
                b[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                if (a >= b[i]) {
                    // 小易能量值 > 怪物防御力
                    // 可直接打败, 增加能量值
                    a += b[i];
                }else {
                    // 小易能量值 < 怪物防御力
                    // 需求两者最大公约数
                    a += gcb(a, b[i]);
                }
            }
            System.out.println(a);
        }
    }

    private static int gcb(int x, int y) {
        // 求最大公约数:
        // 辗转相减法:
        // 较大数 - 较小数, 若差与较小数相等, 则差为 最大公约数
        // 若不等, 则再次比较出较小值相减, 直到两者相等.

        if (x == y) {
            return x;
        }
        return x < y ? gcb(x, y - x) : gcb(x - y, y);
    }

    public static void main2(String[] args) {

        // 老师想知道从某某同学当中，分数最高的是多少，现在请你编程模拟老师的询问。
        // 当然，老师有时候需要更新某位同学的成绩.

        // 输入描述:
        // 输入包括多组测试数据。
        // 每组输入第一行是两个正整数 N 和 M（0 < N <= 30000, 0 < M < 5000）
        // 分别代表学生的数目和操作的数目. 学生 ID 编号从 1 编到 N。
        // 第二行包含 N 个整数，代表这 N 个学生的初始成绩，其中第 i 个数代表 ID 为 i 的学生的成绩
        // 接下来又 M 行，每一行有一个字符 C（只取 ‘Q’ 或 ‘U’），和两个正整数 A, B
        // 当 C 为 'Q' 的时候, 表示这是一条询问操作，他询问 ID 从 A 到 B（包括 A, B）的学生当中，成绩最高的是多少
        // 当 C 为 ‘U’ 的时候，表示这是一条更新操作，要求把 ID 为 A 的学生的成绩更改为 B。

        // 输出描述:
        // 对于每一次询问操作，在一行里面输出最高成绩.
        // 示例:
        // 输入
        // 5 7
        // 1 2 3 4 5
        // Q 1 5
        // U 3 6
        // Q 3 4
        // Q 4 5
        // U 4 5
        // U 2 9
        // Q 1 5
        // 输出
        // 5
        // 6
        // 5
        // 9

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt(); // 学生数目
            int m= in.nextInt(); // 操作数目
            int[] score = new int[n];
            int a = 0, b = 0;
            String c = null;
            for (int i = 0; i < n; i++) {
                score[i] = in.nextInt(); // n 个学生的初始成绩
            }

            for (int j = 0; j < m; j++) {
                // 老师询问 m 次
                c = in.next(); // C 或 U
                a = in.nextInt();
                b = in.nextInt();
                if (c.equals("Q")) {
                    // 不可以 c == Q
                    int max = 0;
                    // 需要判断 a 和 b 哪个更大
                    // 更小的数字做起始数字, 另一个数字做终点数字
                    if (a < b) {
                        for (int i = a - 1; i < b; i++) {
                            // ID 作为数组下标需要减 1, 否则会发生数组越界

                            // 比较 [a,b] 中的最高成绩
                            if (score[i] > max) {
                                max = score[i];
                            }
                        }
                    }else { // a > b
                        for (int i = b - 1; i < a; i++) {
                            // 比较 [b. a] 中的最高成绩
                            if (score[i] > max) {
                                max = score[i];
                            }
                        }
                    }
                    System.out.println(max);
                }
                if (c.equals("U")) {
                    // 更换学生成绩
                    score[a - 1] = b;
                }
            }
        }
    }
}
