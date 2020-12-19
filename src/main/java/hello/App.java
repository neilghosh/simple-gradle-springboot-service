
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.opentelemetry.exporters.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.TracerSdkManagement;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        // Get the tracer management interface
        TracerSdkManagement tracerSdkManagement = OpenTelemetrySdk.getGlobalTracerManagement();

        // Set to export the traces to a logging stream
        tracerSdkManagement.addSpanProcessor(SimpleSpanProcessor.builder(new LoggingSpanExporter()).build());

        SpringApplication.run(App.class, args);
    }

}
