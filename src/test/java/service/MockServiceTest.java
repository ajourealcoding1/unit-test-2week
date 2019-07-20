package service;

import domain.ConvenienceStoreItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import repository.MockRepository;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    // ******************************************
    // 기본 mock test method 연습
    // ******************************************

    @Test
    public void findallTest(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        mockRepository.findAll();

    }

    @Test
    public void findByNameTest(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.setName("사이다");
        mockRepository.findByName("사이다");
        //작성중
    }

    //1500원 사이다와 1500원 포카칩의 가격을 더한 값이 3000원이 맞는지 테스트
    @Test
    public void 사이다와_포카칩_가격을_더하면_3000인지_테스트(){
        when(mockService.findByName("포카칩")).thenReturn(new ConvenienceStoreItem("포카칩", "과자", 1500));
        when(mockService.findByName("사이다")).thenReturn(new ConvenienceStoreItem("사이다", "음료", 1500));

       int totalPrice = mockService.addTwoConvenienceStoreItemPricesByName("사이다","포카칩");
        assertThat(totalPrice,is(3000));
    }

    // 비타오백의 카테고리가 음료였는데 젤리로 변경되는지 테스트
    @Test
    public void 비타오백이_음료였다가_젤리로_변경되는지_테스트(){
        when(mockService.findByName("비타오백")).thenReturn(new ConvenienceStoreItem("비타오백", "음료", 1000));
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem = mockService.findByName("비타오백");
        mockService.updateCategoryByName("비타오백","젤리");
        assertThat(convenienceStoreItem.getCategory(),is("젤리"));
    }

    @Test
    public void 상품카테고리를_가져오면_과자를_리턴() {
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.setCategory("과자");
        when(convenienceStoreItem.getCategory()).thenReturn("과자");
        assertThat(convenienceStoreItem.getCategory(), is("과자"));
    }


    @Test
    public void ShouldReturn치토스WhenGetConvenienceStoreItemName(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        when(convenienceStoreItem.getName()).thenReturn("치토스");

        assertThat(convenienceStoreItem.getName(), is("치토스"));
    }


   @Test(expected = IllegalArgumentException.class)
    public void 스윙칩을저장하면doThrwoException(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        doThrow( new IllegalArgumentException()).when(convenienceStoreItem).setName("스윙칩");
        convenienceStoreItem.setName("스윙칩");
    }


    // 3. verify 를 사용하여 '미드' 카테고리를 저장하는 프로세스가 진행되었는지 테스트 하세요.
   @Test
    public void 미드카테고리저장테스트(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.setCategory("미드");
        verify(convenienceStoreItem).setCategory(anyString());
        verify(convenienceStoreItem, times(2)).setCategory(anyString());
    }


    @Test
    public void 객체크기검증을1번하는지Test(){
        List<ConvenienceStoreItem> convenienceStoreItems = mock(List.class);
        System.out.println(convenienceStoreItems.size()); //실제 호출이 되어야 한다.
        verify(convenienceStoreItems, atLeastOnce()).size();
    }

    @Test
    public void 객체에서getName두번이상하는지Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, atLeast(2)).getName();
    }

    @Test
    public void 객체에서getName세번이하하는지Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, atMost(3)).getName();
    }

    @Test
    public void 객체에서Name설정이안됐을때Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        verify(convenienceStoreItem, never()).setName(any(String.class));
    }

    @Test
    public void 객체에서상품을10분의1초이내에가져오는지Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, timeout(100).atLeast(1)).getName();
    }


    @Test
    public void 상품정보들을Mocking하고Service메소드호출테스트() {

        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("치토스", "과자", 2000));

        String champName = mockService.findByName("치토스").getName();
        assertThat(champName, is("치토스"));
        verify(mockRepository, times(1)).findByName(anyString());
    }

    @Test
    public void 치토스를검색하면_과자카테고리인객체를리턴받고_과자카테고리인지Test(){
        when(mockService.findByName("치토스")).thenReturn(new ConvenienceStoreItem("치토스", "과자", 2000));
        assertThat(mockService.findByName("치토스").getCategory(), is("과자"));
    }


    @Test
    public void 상품리스트를가져와_그갯수가맞는지확인Test(){
        List<ConvenienceStoreItem> convenienceStoreItems = mock(List.class);
        System.out.println(convenienceStoreItems);
        when(convenienceStoreItems.size()).thenReturn(3);
        assertThat(convenienceStoreItems.size(), is(3));
    }

    @Test
    public void 상품을검색하면상품객체를리턴받아_해당method가1번호출되었는지Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("진짬뽕", "컵라면", 1500));
        mockService.findByName("진짬뽕");
        verify(mockRepository, times(1)).findByName(anyString());
    }

    @Test
    public void 상품을검색하면상품객체를리턴받아_가격이일치하는지Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("눈을감자", "과자", 2000));
        mockService.findByName("눈을감자");
        assertThat(mockService.findByName("눈을감자").getPrice(), is(2000));
    }

    // 4. 2개 이상의 가짜 상품 객체를 List로 만들어 리턴하고, 하나씩 해당 객체를 검색한 뒤 검색을 위해 호출한 횟수를 검증하세요.
    @Test
    public void 특정상품을호출하면_그정보를리턴하고_1번이하로호출되었는지Test() {
        given(mockRepository.findByName("불닭볶음면")).willReturn(new ConvenienceStoreItem("불닭볶음면", "라면", 1300));
        ConvenienceStoreItem convenienceStoreItem = mockService.findByName("불닭볶음면");
        verify(mockRepository, times(1)).findByName(anyString());
        assertThat(convenienceStoreItem.getName(), is("불닭볶음면"));

    }

    @Test
    public void 상품을_검색하면_상품객체를_리턴하고_2번이상_호출되었는지_Test() {
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("신라면", "컵라면", 1500));
        mockService.findByName("신라면");
        mockService.findByName("진라면");
        verify(mockRepository, atLeast(2)).findByName(anyString());
    }

    @Test
    public void 어느물품의_가격이_오를때_제대로_올랐는지_Test() {
        when(mockService.findByName("짜파게티")).thenReturn(new ConvenienceStoreItem("짜파게티", "라면", 1000));
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        //System.out.println(mockService.Convenience_price_return("짜파게티"));

        mockService.updatePriceByName("짜파게티", 1300);

        //System.out.println(mockService.findByName("짜파게티").getPrice());
        assertThat(mockService.findByName("짜파게티").getPrice(), is(1300));
    }


}