package osmedile.intellij.stringmanip;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;

public class SwitchFilePathSeparators extends AbstractStringManipAction {

    private char slashFound = ' ';

    @NotNull
    @Override
    public Pair beforeWriteAction(Editor editor, DataContext dataContext) {
        slashFound = ' ';
        return super.beforeWriteAction(editor, dataContext);
    }

    @Override
    public String transformSelection(Editor editor, DataContext dataContext, String s, Object additionalParam) {
        String s1;
        if (slashFound == ' ') {
            if (s.contains("/")) {
                slashFound = '/';
            } else if (s.contains("\\")) {
                slashFound = '\\';
            }
        }
        switch (slashFound) {
            case '/':
                s1 = s.replace("/", "\\");
                break;
            case '\\':
                s1 = s.replace("\\", "/");
                break;
            default:
                s1 = s;
                break;
        }
        return s1;
    }

    @Override
    public String transformByLine(String s) {
        throw new UnsupportedOperationException();
    }
}
