package osmedile.intellij.stringmanip.filter;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import osmedile.intellij.stringmanip.AbstractStringManipAction;

/**
 * @author Daniel Franke
 * @version $Id: $
 */

public class ConvertTabToSpaces extends AbstractStringManipAction {
    private int tabSize = 3;

    protected ConvertTabToSpaces() {
        super();
    }

    protected ConvertTabToSpaces(boolean setupHandler) {
        super(setupHandler);
    }

    @NotNull
    @Override
    public Pair beforeWriteAction(Editor editor, DataContext dataContext) {
        tabSize = editor.getSettings().getTabSize(editor.getProject());
        return super.beforeWriteAction(editor, dataContext);
    }


    @Override
    public String transformByLine(String s) {
        return transformByLine(s, selectionStart);
    }

    public String transformByLine(String s, int lineOffset) {
        int cursorPos = lineOffset;
        StringBuilder buf = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '\t':
                    int fillTo = ((cursorPos + tabSize) / tabSize) * tabSize;
                    while (cursorPos < fillTo) {
                        buf.append(' ');
                        cursorPos++;
                    }
                    break;
                case ' ': {
                    buf.append(' ');
                    cursorPos++;
                }
                break;
                default: {
                    buf.append(c);
                    cursorPos++;
                }

            }
        }
        return buf.toString();
    }
}

