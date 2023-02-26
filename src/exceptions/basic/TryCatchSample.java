package exceptions.basic;

public class TryCatchSample {
    public String readDataFrom(Resource resource) {
        resource.open();

        try {
            String content = resource.read();
            resource.close();
            return content;
        } catch (Exception e) {
            resource.close();
            return "someDefaultValue";
        }
    }
}
