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

    // 4. ConveniencestoreItem 객체의 크기를 검증하는 로직이 1번 실행되었는지 테스트 하세요.
    @Test
    public void 객체크기검증Test(){
        //ConvenienceStoreItem champion = mock(ConvenienceStoreItem.class);
        List<ConvenienceStoreItem> convenienceStoreItems = mock(List.class);
        System.out.println(convenienceStoreItems.size()); //실제 호출이 되어야 함
        verify(convenienceStoreItems, atLeastOnce()).size();
    }


    // 4-1. ConveniencestoreItem 객체에서 상품이름을 가져오는 로직이 2번 이상 실행되면 Pass 하는 로직을 작성하세요.
    @Test
    public void 두번이상Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, atLeast(2)).getName();
    }

    // 4-2. ConveniencestoreItem 객체에서 상품이름을 가져오는 로직이 최대 3번 이하 실행되면 Pass 하는 로직을 작성하세요.
    @Test
    public void 세번이하Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, atMost(3)).getName();
    }

    // 4-3. ConveniencestoreItem 객체에서 상품이름을 저장하는 로직이 실행되지 않았으면 Pass 하는 로직을 작성하세요.
    @Test
    public void 실행안됐을때Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);

        verify(convenienceStoreItem, never()).setName(any(String.class));
    }

    // 4-4. ConveniencestoreItem 객체에서 상품을 가져오는 로직이 200ms 시간 이내에 1번 실행되었는 지 검증하는 로직을 작성하세요.
    @Test
    public void 이백이내Test(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        convenienceStoreItem.getName();
        convenienceStoreItem.getName();
        verify(convenienceStoreItem, timeout(200).atLeast(1)).getName();
    }

    // ******************************************
    // injectmock test 연습
    // ******************************************

    @Test
    public void 상품정보들을Mocking하고Service메소드호출테스트() {

        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("조이", "바텀", 2));

        String champName = mockService.findByName("조이").getName();
        assertThat(champName, is("조이"));
        verify(mockRepository, times(1)).findByName(anyString());

    }

    // 1. 리산드라라는 상품 이름으로 검색하면 미드라는 카테고리와 함께 가짜 객체를 리턴받고, 카테고리가 탑이 맞는지를 테스트하세요
    @Test
    public void 리산드라검색(){
        when(mockService.findByName("리산드라")).thenReturn(new ConvenienceStoreItem("리산드라", "미드", 2));
        assertThat(mockService.findByName("리산드라").getCategory(), is("미드"));

    }


    // 2. 2개 이상의 상품을 List로 만들어 전체 상품을 가져오는 메소드 호출시 그 갯수가 맞는지 확인하는 테스트 코드를 작성하세요.
    @Test
    public void 리스트갯수확인Test(){
        List<ConvenienceStoreItem> convenienceStoreItems = mock(List.class);
        System.out.println(convenienceStoreItems);
        when(convenienceStoreItems.size()).thenReturn(3);
        assertThat(convenienceStoreItems.size(), is(3));
    }

    // 3. 상품을 검색하면 가짜 상품 객체를 리턴하고, mockRepository의 해당 메소드가 1번 호출되었는지를 검증하고, 그 상품의 가격이
    //    맞는지 확인하는 테스트코드를 작성하세요.
    @Test
    public void 삼번(){
        ConvenienceStoreItem convenienceStoreItem = mock(ConvenienceStoreItem.class);
        when(mockService.findByName(anyString())).thenReturn(new ConvenienceStoreItem("리산드라", "미드", 2));
        mockService.findByName("베인");
        verify(mockRepository, times(1)).findByName(anyString());
        assertThat(mockService.findByName("리산드라").getPrice(), is(2));

    }

    // 4. 2개 이상의 가짜 상품 객체를 List로 만들어 리턴하고, 하나씩 해당 객체를 검색한 뒤 검색을 위해 호출한 횟수를 검증하세요.


    //가장 많이 사용되는 테스트 중 하나로 BDD 방식에 기반한 테스트 방법 예제
    @Test
    public void 탐켄치를_호출하면_탐켄치정보를_리턴하고_1번이하로_호출되었는지_검증() {

        /*
        //given
        given(mockRepository.findByName("탐켄치")).willReturn(new ConvenienceStoreItem("탐켄치", "서폿", 4));
        //when
        ConvenienceStoreItem convenienceStoreItem = mockService.findByName("탐켄치");
        //then
        verify(mockRepository, atLeast(1)).findByName(anyString());
        assertThat(convenienceStoreItem.getName(), is("탐켄치"));
        */
        given(mockRepository.findByName("자야")).willReturn(new ConvenienceStoreItem("자야", "서폿", 4));
        ConvenienceStoreItem convenienceStoreItem = mockService.findByName("자야");
        convenienceStoreItem = mockService.findByName("자야");
        verify(mockRepository, times(2)).findByName(anyString());
        assertThat(convenienceStoreItem.getName(), is("자야"));

    }


}