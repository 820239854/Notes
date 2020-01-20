>In computer science, in the context of data storage, serialization (or serialisation) is the process of translating data structures or object state into a format that can be stored (for example, in a file or memory buffer) or transmitted (for example, across a network connection link) and reconstructed later (possibly in a different computer environment).  
-- wiki

Why do we need Serialization?
```Java
class Person{
  string name;
  int id;
}

class Group{
  string name;
  List<Person> members;
}

// No problem if running locally.
void ProcessGroup(Group group){
  print(group.name);
  for(Person person : group.members){
    print(person.id + ":" + person.name);
  }
}
```
For a complex object,"data" are everywhere in memory.How can we store this object to a file?
> group等为所指对象的内存指针值
  length等为数据本身

How can we pass this object to
* another process for Inter-process communciation(IPC)
* another machine for remote procedure call(RPC)?

Types of Serialization
* Text
  * 可读性好,利用率低
* Binary
  * Fast and Compact(No serialisation at all,just dump the memory)
  * can't have dynamic allocation
  * can't add new fields
  * Non-portable
    * padding / alignment / big-endian

Tools:
* XML(outdated)
* JSON(now)
* ProtoBuf(future)
* Ajax(deprecated)
* QueryString(也是Text \ Readable Serialization)

Warnings:
* Encoding
  * Text serialisation must obey the Encoding.
  * UTF-8 可变长(兼容ASCII),效率高,对中文不友好
  * UTF-16
* Endiannes
  * Little-endian:字符末尾在内存起始位置(x86,x64)
  * Big-endian:字符开头在内存起始位置

Use Case:
* Transfer data
* Deep copy

Ref:
[花花]: url "https://www.bilibili.com/video/av84078665"
