package hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;

@RestController("/")
public class HelloGradleController {

    @GetMapping
    public String helloGradle() {
        Tracer tracer = OpenTelemetry.getGlobalTracer("instrumentation-library-name", "1.0.0");
        Span span = tracer.spanBuilder("my span").startSpan();
        try (Scope scope = span.makeCurrent()) {
            Thread.sleep(2000);
        } catch (Throwable t) {
            span.setStatus(StatusCode.ERROR, "Change it to your error message");
        } finally {
            span.end(); // closing the scope does not end the span, this has to be done manually
        }

        return "Hello Gradle!";
    }

}
