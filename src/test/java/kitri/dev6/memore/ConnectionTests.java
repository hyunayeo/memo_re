package kitri.dev6.memore;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;



public class ConnectionTests {
    @Test
    public void connection_test() throws Exception {
        Long id =1L;
        // then
        assertThat(id).isEqualTo(1);
    }
}