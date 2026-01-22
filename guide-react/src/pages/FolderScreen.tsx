import "../style/layout.css";
import "../style/style.css";

import ImgGuide from "../components/ImgGuide.tsx";
import Header from "../components/Header.tsx";
import Note from "../components/Note.tsx";

export default function FolderScreen() {
    return (
        <>
        <Header/>
        <main>
        <h2>UI Structure and function</h2>
        <section>
            <h3>Sorting methods</h3>
            <p>Once we open the app, we will see the following screen:</p>
            <ImgGuide alt="Default app look" caption={"This is what the app looks like after opening it for the first time"} path="/FolderScreen/default.png"/>
            <p>The top section allows the user to select the sorting method. There are currently two supported sorting methods:</p>
            <dl>
                <dt>YYYYMMDD (default)</dt>
                <dd>Most cameras and messaging apps use naming conventions to label their pictures. We can find out when the picture was taken (or sent) by getting the date from the file name. This method tends to give more accurate results, but some files may not be categorized depending on their naming.</dd>
                <dt>File date attribute</dt>
                <dd>Alternatively, we can use the date attribute that the file system uses. All files have one, but this method may not be too accurate. For instance, new copies of old pictures could be flagged as recent pictures.</dd>
            </dl>
            <p>We can switch between the two by clicking the arrow button:</p>
            <ImgGuide alt="List of available sorting methods" caption="Drop-down menu on full display" path="/FolderScreen/combo-open.png"/>
            <p>More sorting methods could be added in the future</p>
            
        </section>
        <section>
            <h3>Folder selection</h3>
            <p>The next part of the UI are the source and target folder selectors. The user can either type the desired folder route or click on the "Select folder" button to choose it using the file explorer.</p>
            <ImgGuide alt="Example of the file choosing dialog" caption="Example of the file choosing dialog" path="FolderScreen/select-folder2.png"/>
            <Note title="Important" content="The target folder should not be a child folder of the source folder, they should be kept separate."/>
        </section>
        <section>
            <h3>Recursivity</h3>
            <p>The bottom part of the UI shows a checkbox labeled as "Recursive"</p>
            <ImgGuide alt="Selected checkbox" caption="In this case, the user has turned on the check-box" path="FolderScreen/recursive.png"/>
            <p>By default this option is turned off, clicking proceed would cause the program to only check files in the target folder (not in subfolders). When the search is marked as recursive, the app also copies files from subfolders.</p>
        </section>
        <section>
            <h3>Keeping your settings</h3>
            <p>Upon closing, the app will save the user's settings in a "manager.config" file inside its directory. Deleting this file is safe, but it will refresh the app configuration to its default values. If a valid config file is present, the program will remember the parameters from the last search.</p>
        </section>
        <section>
            <h3>Proceeding</h3>
            <p>One we are done configuring the other parameters, we can now click the "Proceed button". The app will first check that the input parameters are valid. We can get an error message in case any of them are wrong.</p>
            <ImgGuide alt="Error prompt example" caption="In this case, the destination folder route is not a valid path" path="FolderScreen/error.png"/>
            <p>Although uncommon, a similar messages will show up in case anything goes wrong in the copying process itself.</p>
        </section>
        </main>
        </>
    );
}