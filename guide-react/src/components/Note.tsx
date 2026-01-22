import type { ReactNode } from "react";

export default function Note({title, content, children = null} : {title:string, content:string, children?:ReactNode}) {
    return (
        <div className="note">
            <p className="note-title">{title}</p>
            <div>
            <p className="note">{content}</p>
            {children}
            </div>
        </div>
    );
}