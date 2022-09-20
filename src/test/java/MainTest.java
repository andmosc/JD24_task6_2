import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainTest {

    private static Person person1,person2,person3,person4;
    private static Person person5,person6,person7,person8;

    @BeforeAll
    public static void setUp() {
        person1 = new Person("Maria1","Ivanova1",17,Sex.WOMAN,Education.HIGHER);
        person2 = new Person("Maria2","Ivanova2",18,Sex.WOMAN,Education.HIGHER);
        person3 = new Person("Maria3","Ivanova3",26,Sex.WOMAN,Education.ELEMENTARY);
        person4 = new Person("Maria4","Ivanova4",61,Sex.WOMAN,Education.HIGHER);

        person5 = new Person("Oleg1","Ivanov1",17,Sex.MAN,Education.HIGHER);
        person6 = new Person("Oleg2","Ivanov2",18,Sex.MAN,Education.HIGHER);
        person7 = new Person("Oleg3","Ivanov3",20,Sex.MAN,Education.ELEMENTARY);
        person8 = new Person("Oleg4","Ivanov4",66,Sex.MAN,Education.HIGHER);
    }

    @Test
    public void testNumberMinors() {
        List<Person> listPerson = Arrays.asList(person1,person2,person3);
        Long expected = Main.numberMinors(listPerson);
        assertThat(expected,allOf(lessThanOrEqualTo(18L)));
    }

    @Test
    public void testPersonsAddNotEmpty() {
        List<String> firstName = Arrays.asList("Ivan","Ivanov");
        List<String> surName = Arrays.asList("Petr","Petrov");
        List<Person> expected = Main.personsAdd(firstName,surName,19);
        assertThat(expected,is(not(empty())));
    }
    @Test
    public void testListWorkable() {
        List<Person> listPerson = Arrays.asList(person1,person2,
                person3,person4,person5,person6,person7,person8);

        List<Person> expected = Main.listWorkable(listPerson);

        assertThat(expected,contains(person2,person6));
    }
}

