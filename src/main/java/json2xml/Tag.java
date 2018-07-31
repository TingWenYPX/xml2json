package json2xml;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to store XML hierarchy
 *
 */
public class Tag {

    private String mPath;
    private String mName;
    private ArrayList<Tag> mChildren = new ArrayList<>();
    private String mContent;

    /* package */ Tag(String path, String name) {
        mPath = path;
        mName = name;
    }

    /* package */ void addChild(Tag tag) {
        mChildren.add(tag);
    }

    /* package */ void setContent(String content) {
        // checks that there is a relevant content (not only spaces or \n)
        boolean hasContent = false;
        if (content != null) {
            for(int i=0; i<content.length(); ++i) {
                char c = content.charAt(i);
                if ((c != ' ') && (c != '\n')) {
                    hasContent = true;
                    break;
                }
            }
        }
        if (hasContent) {
            mContent = content;
        }
    }

    /* package */ String getName() {
        return mName;
    }

    /* package */ String getContent() {
        return mContent;
    }

    /* package */ ArrayList<Tag> getChildren() {
        return mChildren;
    }

    /* package */ boolean hasChildren() {
        return (mChildren.size() > 0);
    }

    /* package */ int getChildrenCount() {
        return mChildren.size();
    }

    /* package */ Tag getChild(int index) {
        if ((index >= 0) && (index < mChildren.size())) {
            return mChildren.get(index);
        }
        return null;
    }

    /* package */ HashMap<String, ArrayList<Tag>> getGroupedElements() {
        HashMap<String, ArrayList<Tag>> groups = new HashMap<>();
        for(Tag child : mChildren) {
            String key = child.getName();
            ArrayList<Tag> group = groups.get(key);
            if (group == null) {
                group = new ArrayList<>();
                groups.put(key, group);
            }
            group.add(child);
        }
        return groups;
    }

    /* package */ String getPath() {
        return mPath;
    }

    @Override
    public String toString() {
        return "Tag: " + mName + ", " + mChildren.size() + " children, Content: " + mContent;
    }
}
