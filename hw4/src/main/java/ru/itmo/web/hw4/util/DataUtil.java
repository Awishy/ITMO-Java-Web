package ru.itmo.web.hw4.util;

import ru.itmo.web.hw4.model.Color;
import ru.itmo.web.hw4.model.Post;
import ru.itmo.web.hw4.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", Color.GREEN),
            new User(6, "pashka", "Pavel Mavrin", Color.RED),
            new User(9, "geranazavr555", "Georgiy Nazarov", Color.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", Color.RED),
            new User(13, "someone", "Ivan Ivanov", Color.GREEN),
            new User(15, "valentin02", "Valentin Dmitrievich", Color.BLUE)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1, "Test post",
                    """
                            Hey there! This post should be displayed entire.
                            """
                    , 15),
            new Post(2, "touristream 011: Codeforces Round 670 (Div. 2) + Educational 95",
                    """
                            Hey there!!
                                                                             
                                                                             During my last stream (link to YouTube), besides the expected SNSS Round 5, I did Codeforces Round 669 (Div. 2) virtually trying to explain most of my thoughts in the process. I'd say it went pretty well!
                                                                             
                                                                             Tomorrow I'll hold another stream solving the most recent Codeforces Div. 2 rounds: Round 670 and Educational 95. I might also do today's AtCoder Beginner Contest 178.
                                                                             
                                                                             I'm planning to go live right after Educational 95 finishes — that is, around 19:45 MSK.
                                                                             
                                                                             A question is, given that I will do both Round 670 and Educational 95, which one should I do first? Vote here :)
                                                                             
                                                                             As usual, the stream will be live on my Twitch, make sure to follow and get instant notifications. Stream recording will be posted to my YouTube afterwards.
                            """
                    , 11),
            new Post(3, "Testlib: tests + CI",
                    """
                            Hello, Codeforces.
                                                        
                            I have wanted for a long time and now, finally, I sat down and did it.
                                                        
                            Many of you are familiar with my Testlib project. This is a library that helps you develop problems if you are using C++. In the case of problems for Codeforces rounds, its use is mandatory. It seems to me that it is almost a de facto standard for preparing materials for completely different olympiads and competitions.
                                                        
                            The history of С++-Testlib is almost lost in centuries, I published the first version in 2005.
                                                        
                            Nowadays, making edits to Testlib has become tricky. The code grown to thousands of lines, also it has too long a history. This increases the likelihood of accidentally changing some aspect of behavior, which will jeopardize the holding of a Codeforces round or another important competition. In short, making a bug in this code or changing behavior anywhere is highly discouraged.
                                                        
                            So I recently took the plunge and wrote some tests for Testlib. Here's what tests can do now:
                                                        
                            check that all cpp files in the repository are compiled (test test-000_compile-all-cpp)
                            check the plausibility of the behavior of a simple sval.cpp validator on a series of files (test-001_run-sval test)
                            check the plausibility of the behavior of the two checkers fcmp.cpp and wcmp.cpp on a series of files (test test-002_run-fcmp-wcmp)
                            check that the behavior of the random number generator has not changed (test test-003_run-rnd)
                            check the behavior of some functions and the behavior of InStream and opt in a series of unit tests (test test-004_use-test.h)
                            More good news. All this was automated through GitHub Actions. Tests are automatically run on any push or pull request. The launch is carried out on:
                                                        
                            6 different operating systems (ubuntu-18.04, ubuntu-22.04, macos-11, macos-12, windows-server-2019, windows-server-2022),
                            different versions of different C++ compilers (g ++, clang ++, msvc),
                            different C ++ standards
                            and both architectures (32 and 64 bits) are used.
                            In total tests run in 33 jobs! Look here https://github.com/MikeMirzayanov/testlib/actions/runs/2733832393
                                                        
                            Of course, there are still few tests. Something else I will definitely add.
                                                        
                            If you understand well how some functionality of Testlib works, then you can add a test for it. First of all, consider adding unit-tests to test-004_use-test.h (carefully study the design of this test).
                                                        
                            Otherwise you might find it useful to use test-ref script in tests, which checks that a process has exited with the expected exit code, standard output, and standard error.
                                                        
                            It is highly desirable to cover all new functionality with tests. If the functionality is such that it is not very difficult to write tests, then the presence of tests is required.
                                                        
                            How to run tests locally?
                            You need to have installed bash and some tools. After just do cd tests && bash run.sh.
                                                        
                            This will run all tests on all supported installed compilers on multiple C++ standards. Probably, just want to run tests on single g++ version using --std=c++11. You can use bash run.sh g++ v0 g++.
                                                        
                            More examples:
                                                        
                            bash run.sh — to run all tests on all compilers on multiple standards
                            bash run.sh g++ — to run all tests on g++ on multiple standards
                            bash run.sh g++ 11 v10 — to run all tests on g++-10 with --std=c++11
                            bash run.sh g++ 11 test-001_run-sval — to run test-001_run-sval on g++ with --std=c++11
                            bash run.sh test-001_run-sval — to run test-001_run-sval on all compilers on multiple standards
                            bash run.sh g++ 11 17 — to run all tests on g++ with --std=c++11 and with --std=c++17
                            Also you can use t.sh (or t.bat) to run shortcut of run.sh g++ 11 v0 $*. It is good idea to use it for local development to have quick response.
                            """
                    , 1),
            new Post(5, "ITMO Algorithms Course",
                    """
                               Hello Codeforces!
                               
                               I teach a course on algorithms and data structures at ITMO University. During the last year I was streaming all my lectures on Twitch and uploaded the videos on Youtube.
                               
                               This year I want to try to do it in English.
                               
                               This is a four-semester course. The rough plan for the first semester:
                               
                               Algorithms, complexity, asymptotics
                               Sorting algorithms
                               Binary heap
                               Binary search
                               Linked lists, Stack, Queue
                               Amortized analysis
                               Fibonacci Heap
                               Disjoint Set Union
                               Dynamic Programming
                               Hash Tables
                               The lectures are open for everybody. If you want to attend, please fill out this form to help me pick the optimal day and time.
                               
                               See you!
                            """
                    , 6),
            new Post(9, "Codeforces Round #826 (Div. 3)",
                    """
                                    Привет! В вторник, 11 октября 2022 г. в 17:35 начнётся Codeforces Round 826 (Div. 3) — очередной Codeforces раунд для третьего дивизиона. В этом раунде будет 6-8 задач, которые подобраны по сложности так, чтобы составить интересное соревнование для участников с рейтингами до 1600. Однако все желающие, чей рейтинг 1600 и выше могут зарегистрироваться на раунд вне конкурса.
                                                        
                                    Раунд пройдет по правилам образовательных раундов. Таким образом, во время раунда задачи будут тестироваться на предварительных тестах, а после раунда будет 12-ти часовая фаза открытых взломов. Мы постарались сделать приличные тесты — так же как и вы, мы будем расстроены, если у многих будут падать решения после окончания контеста.
                                                        
                                    Вам будет предложено 6-8 задач и 2 часа 15 минут на их решение.
                                                        
                                    Штраф за неверную попытку в этом раунде будет равняться 10 минутам.
                                                        
                                    Напоминаем, что в таблицу официальных результатов попадут только достоверные участники третьего дивизиона. Как написано по ссылке — это вынужденная мера для борьбы с неспортивным поведением. Для квалификации в качестве достоверного участника третьего дивизиона надо:
                                                        
                                    принять участие не менее чем в пяти рейтинговых раундах (и решить в каждом из них хотя бы одну задачу)
                                    не иметь в рейтинге точку 1900 или выше.
                                    Независимо от того являетесь вы достоверными участниками третьего дивизиона или нет, если ваш рейтинг менее 1600, то раунд для вас будет рейтинговым.
                            """
                    , 1)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
