
export default function ImgGuide({path, caption, alt} : {path : string, caption:string, alt:string}) {
    return (
        <figure>
            <img src={path} alt={alt}/>
            <figcaption>{caption}</figcaption>
        </figure>
    );
}