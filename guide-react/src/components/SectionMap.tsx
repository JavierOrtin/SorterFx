import React, { useEffect, useState } from "react";

const defaultTitle = "In this section...";
const defaultCollapsible = true;
const defaultHeaderLevel = "h3";

type SectionItem = {
  id: string;
  label: string;
};

interface SectionMapProps {
  title?: string;
  sections: SectionItem[];
  collapsible?: boolean;
  headerLevel?:string;
}

function SectionMap({ title = defaultTitle, sections, collapsible = defaultCollapsible, headerLevel = defaultHeaderLevel}: SectionMapProps) {
  const content = (
    <ul className="section-index-list">
      {sections.map((section) => (
        <li key={section.id}>
          <a href={`#${section.id}`}>{section.label}</a>
        </li>
      ))}
    </ul>
  );

  
  if (collapsible) {
      return (
          <aside className="section-index">
        <details>
          <summary>{title}</summary>
          {content}
        </details>
      </aside>
    );
}

const header = React.createElement(headerLevel, {}, title);
  return (
    <aside className="section-index">
      {header}
      {content}
    </aside>
  );
}

export default function SectionMapAuto({title = defaultTitle, collapsible = defaultCollapsible, headerLevel = defaultHeaderLevel}
: {title?:string, collapsible?:boolean, headerLevel?:string}) {  

    const [sections, setSections] = useState<SectionItem[]>([]);
    useEffect(() => {
        const headers = Array.from(document.querySelectorAll(`main ${headerLevel}`)).filter((h) => h.textContent !== title);
        const newSections = headers.map(header => {
            let id = header.id
            if(! header.id) {
                id = header.textContent?.replace(/\s+/g, "-").toLowerCase() || "";
                header.id = id;
            }
            return {id:id, label : header.textContent ||""}
        });
        setSections(newSections);
    }, []);
    return <SectionMap sections={sections} collapsible={collapsible} title={title}/>;
}