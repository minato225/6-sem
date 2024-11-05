using System;
using System.Linq;
using System.IO;
using System.Security.Cryptography.X509Certificates;
using System.Text.RegularExpressions;
using Newtonsoft.Json;

[Obsolete]
public static string GetPublisher(string path){
    string publisher;
    try{
    	var fullName = X509Certificate.CreateFromSignedFile(path).GetName();
        publisher = Regex.Match(fullName, @"O=""(.+?)"",").Groups[1].Value;
    }
    catch (Exception){
	publisher = string.Empty;
    }

    return publisher;
}

const string rootPath = @"D:\6sem";
var root = Directory.Exists(rootPath)
                ? new DirectoryInfo(rootPath)
                : throw new DirectoryNotFoundException($"Directory '{rootPath}' not found.");

var info = from file in root.EnumerateFiles("*.exe", SearchOption.AllDirectories)
	   where filter(file.Name)
           group file by GetPublisher(file.FullName) into publisher
           orderby publisher.Count() descending
           select new {
                Publisher = publisher.Key,
                Files = string.Join(", ", publisher.Select(f => f.Name)),
                Size = publisher.Sum(x => x.Length).ToString()
           };

var json = JsonConvert.SerializeObject(info, Formatting.Indented);
Console.WriteLine(json);