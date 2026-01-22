export default function Note({title, content} : {title:string, content:string}) {
    return (
        <div className="note">
            <p className="note-title">{title}</p>
            <p className="note">{content}</p>
        </div>
    );
}