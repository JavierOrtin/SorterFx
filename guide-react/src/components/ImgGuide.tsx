
export default function ImgGuide({path, caption, alt} : {path : string, caption:string, alt:string}) {
    if(path.charAt(0) !== "/") path = `/${path}`;
    return (
        <figure>
            <img src={`.${path}`} alt={alt}/>
            <figcaption>{caption}</figcaption>
        </figure>
    );
}