# maoshan

关于`RestTemplate`的用法，可以参阅下面代码片段

```java
@Slf4j
@Service
public class ConsumerBusinessImpl implements ConsumerBusiness {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AccountDetailVO viewAccountDetail(String token, AccountDetailRequest param) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
            body = mapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

        String apiUrl = "http://127.0.0.1:19000/provider/account/detail";
        HttpMethod httpMethod = HttpMethod.POST;
        ResponseEntity<HuaResult<AccountDetailVO>> responseEntity = restTemplate.exchange(
                apiUrl,
                httpMethod,
                httpEntity,
                new ParameterizedTypeReference<HuaResult<AccountDetailVO>>() {
                }
        );

        Assert.notNull(responseEntity, "responseEntity为空");
        HuaResult<AccountDetailVO> huaResult = responseEntity.getBody();
        Assert.notNull(huaResult, "result不能为空");
        Assert.isTrue(huaResult.getIsSuccess(), "code:" + huaResult.getResultCode() + " message:" + huaResult.getResultMessage());
        AccountDetailVO accountDetail = huaResult.getData();
        return accountDetail;
    }
}
```