package stepImpl;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.support.PageFactory;
import pages.OratorPage;
import java.io.IOException;
import java.util.List;
import static util.DriverSetup.androidDriver;


/**
 * Created by Osanda on 4/29/2017.
 */


public class OratorPageStepDefinitions {

    private OratorPage oratorPage = PageFactory.initElements(androidDriver, OratorPage.class);

    @Step("Validate that the application can read the text <table>")
    public void oratorSpeakTest(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            oratorPage.speak(row.getCell(columnNames.get(0)));
        }
    }


}
