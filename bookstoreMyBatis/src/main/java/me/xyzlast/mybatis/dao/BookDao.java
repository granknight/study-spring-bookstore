package me.xyzlast.mybatis.dao;

import me.xyzlast.mybatis.entities.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by ykyoon on 1/13/14.
 */
public interface BookDao extends EntityDao<Book> {
//    @Select(value = "SELECT id, name, author, publishDate, status FROM books Order By Status")
    List<Book> listByStatus();

//    @Select(value = "SELECT id, name, author, publishDate, status FROM books")
    List<Book> getAll();

//    @Insert(value = "INSERT INTO books(name, author, publishDate, comment, status, rentUserId) VALUES(#{name}, #{author}, #{publishDate}, #{comment}, #{status.ordinal}, #{rentUserId})")
    void add(Book book);

//    @Update(value = "UPDATE books SET name=#{name}, author=#{author}, publishDate=#{publishDate}, comment=#{comment}, status=#{status.ordinal}, rentUserId=#{rentUserId} WHERE id = #{id}")
    void update(Book book);

//    @Delete(value = "DELETE FROM books")
    void deleteAll();

//    @Select(value = "SELECT COUNT(*) FROM books")
    int countAll();
}
