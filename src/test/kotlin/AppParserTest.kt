import model.App
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import parser.CSVParser
import utilities.Constant
import utilities.Converter

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class AppParserTest{

    private lateinit var parser: CSVParser
    private lateinit var converter: Converter

    @BeforeAll
    fun setup(){
        converter= Converter()
    }

    @Test
    fun should_ReturnEmptyList_When_FileNotFound(){
        //Given filename that not found
        parser = CSVParser("file",converter)
        //when open the file to parse
        val result = parser.getAllApps()
        //then check the result
        Assertions.assertEquals(mutableListOf<App>(),result)
    }




    @Test
    fun should_ReturnListOfApp_When_CorrectFileName(){
        //Given filename
        parser = CSVParser(Constant.CSV_FILE_NAME,converter)
        //when open the file to parse
        val result = parser.getAllApps()
        //then check the result
        Assertions.assertEquals(4426,result.size)
    }
}