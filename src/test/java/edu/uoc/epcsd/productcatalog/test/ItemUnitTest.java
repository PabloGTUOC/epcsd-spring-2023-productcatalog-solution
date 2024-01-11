package edu.uoc.epcsd.productcatalog.test;
import edu.uoc.epcsd.productcatalog.domain.Item;
import edu.uoc.epcsd.productcatalog.domain.ItemStatus;
import edu.uoc.epcsd.productcatalog.domain.repository.ItemRepository;
import edu.uoc.epcsd.productcatalog.domain.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ItemUnitTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemUnitTest.class);
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private KafkaTemplate<String, Object> productKafkaTemplate;
    @InjectMocks
    private ItemServiceImpl itemService;
    @BeforeEach
    public void setUp() {
        Item item = new Item("1234567890", ItemStatus.OPERATIONAL, 1L);
        when(itemRepository.findBySerialNumber("1234567890")).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);
        LOGGER.info("Set Up completed");
    }
    @Test
    public void whenItemNonOp_thenStatusNonOp() {
        LOGGER.info("Starting test for Ops status");
        // Triggering on the test
        LOGGER.info("Calling setOperational on itemService");
        Item updatedItem = itemService.setOperational("1234567890", false);
        // Assert the test
        LOGGER.info("Asserting that the item status is NON_OPERATIONAL");
        assertThat(updatedItem.getStatus()).isEqualTo(ItemStatus.NON_OPERATIONAL);
        LOGGER.info("Test completed");
    }
}
